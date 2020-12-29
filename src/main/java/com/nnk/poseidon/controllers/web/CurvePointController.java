package com.nnk.poseidon.controllers.web;

import com.nnk.poseidon.constants.ApiUrlConstants;
import com.nnk.poseidon.dto.CurvePointDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

/**
 * The type Curve controller.
 * This controller provide web services that allow CRUD operations on CurvePoint
 *
 * @author Yahia CHERIFI
 */

@Controller
@RequestMapping("/curvePoint")
public class CurvePointController {

    /**
     * Class logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(CurvePointController.class);

    /**
     * Static String of the curvePoint attribute.
     */
    private static final String CURVE_POINT_ATTRIBUTE = "curvePoint";

    /**
     * Static String for the redirection link.
     */
    private static final String REDIRECTION_URL =
            "redirect:/curvePoint/list";

    /**
     * RestTemplate to inject.
     */
    private final RestTemplate template;

    /**
     * Instantiates a new CurvePointController.
     * @param restTemplate RestTemplate instance that is used for
     *                     consuming the API
     */
    @Autowired
    public CurvePointController(final RestTemplate restTemplate) {
        this.template = restTemplate;
    }

    /**
     * Home web page for CurvePoint.
     *
     * @param model the model
     * @return an html page in which all existing curvePoints will be displayed
     */
    @GetMapping("/list")
    public String home(final Model model) {
        LOGGER.debug("GET request sent from home method"
                + " of the CurvePointController to load all CurvePoints");
        String findAllCurvePointUrl = ApiUrlConstants.CURVE_POINT_API_BASE_URL
                + "/findAll";
        ResponseEntity<List<CurvePointDTO>> responseEntity = template.exchange(
                findAllCurvePointUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CurvePointDTO>>() { }
        );
        model.addAttribute("curvePointList", responseEntity.getBody());
        return "curvePoint/list";
    }

    /**
     * Display the add CurvePoint form.
     *
     * @param model the model
     * @return an html form(view page)
     */
    @GetMapping("/add")
    public String addCurvePointForm(final Model model) {
        LOGGER.debug("GET request sent from addCurvePointForm of the"
                + " CurvePointController to load the AddCurvePointForm");
        CurvePointDTO curvePointToSave = new CurvePointDTO();
        model.addAttribute(CURVE_POINT_ATTRIBUTE, curvePointToSave);
        return "curvePoint/add";
    }

    /**
     * Saving a valid CurvePoint.
     *
     * @param curvePoint the CurvePoint to save.
     * @param result     the result
     * @param model      the model
     * @return the string
     */
    @PostMapping("/validate")
    public String validate(@Valid
                           @ModelAttribute("curvePoint")
                           final CurvePointDTO curvePoint,
                           final BindingResult result, final Model model) {
        LOGGER.debug("POST request sent from the validate method of the"
                + " CurvePointController to save a new CurvePoint");
        String addCurvePointUrl = ApiUrlConstants.CURVE_POINT_API_BASE_URL
                + "/add";
        if (!result.hasErrors()) {
            curvePoint.setCreationDate(
                    new Timestamp(System.currentTimeMillis()));
            HttpEntity<CurvePointDTO> httpEntity = new HttpEntity<>(curvePoint);
            template.exchange(
                    addCurvePointUrl,
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );
            LOGGER.info("CurvePoint {} saved successfully",
                    curvePoint.getCurveId());
            return REDIRECTION_URL;
        }
        LOGGER.error("Failed to save the CurvePoint."
                + " Request redirected to the AddForm");
        model.addAttribute(CURVE_POINT_ATTRIBUTE, curvePoint);
        return "curvePoint/add";
    }

    /**
     * Show the CurvePoint update form.
     *
     * @param id    the id of the CurvePoint to update
     * @param model the model
     * @return the html form
     */
    @GetMapping("/update")
    public String showUpdateForm(@RequestParam("id") final Integer id,
                                 final Model model) {
        LOGGER.debug("GET request sent from the showUpdateForm of the "
                + " CurvePointController to update CurvePoint {}", id);
        try {
            String findCurveByIdUrl = ApiUrlConstants.CURVE_POINT_API_BASE_URL
                    + "/findById/" + id;
            ResponseEntity<CurvePointDTO> responseEntity = template.exchange(
                    findCurveByIdUrl,
                    HttpMethod.GET,
                    null,
                    CurvePointDTO.class
            );
            model.addAttribute(CURVE_POINT_ATTRIBUTE,
                    responseEntity.getBody());
            LOGGER.info("Update form loaded successfully"
                    + " to update CurvePoint {}", id);
        } catch (HttpServerErrorException e) {
            LOGGER.error("Failed to load CurvePoint {}", id);
            return "404NotFound/404";
        }
        LOGGER.info("Update form and CurvePoint {} loaded successfully", id);
        return "curvePoint/update";
    }

    /**
     * Update CurvePoint.
     *
     * @param id         the id of the CurvePoint
     * @param curvePoint the curve point
     * @param result     the result
     * @param model      the model
     * @return the CurvePoint home page or the Update form
     */
    @PostMapping("/update/{id}")
    public String updateCurvePoint(@PathVariable("id") final Integer id,
                                   @Valid @ModelAttribute("curvePoint")
                                   final CurvePointDTO curvePoint,
                            final BindingResult result, final Model model) {
        LOGGER.debug("POST request sent from updateCurvePoint of the"
                + " CurvePointController to update CurvePoint {}", id);
        String updateCurvePoint = ApiUrlConstants.CURVE_POINT_API_BASE_URL
                + "/update/" + id;
        if (!result.hasErrors()) {
            HttpEntity<CurvePointDTO> httpEntity = new HttpEntity<>(curvePoint);
            template.exchange(
                    updateCurvePoint,
                    HttpMethod.PUT,
                    httpEntity,
                    String.class
            );
            LOGGER.info("CurvePoint {} updated successfully."
                    + " Redirecting to CurvePoint list", id);
            return REDIRECTION_URL;
        }
        LOGGER.error("CurvePoint {} update Failed."
                + " Redirecting to the Request to the updateForm", id);
        model.addAttribute(CURVE_POINT_ATTRIBUTE, curvePoint);
        return "curvePoint/update";
    }

    /**
     * Delete CurvePoint.
     *
     * @param id the id of the CurvePoint to delete
     * @return the CurvePoint home page
     */
    @GetMapping("/delete")
    public String deleteCurve(@RequestParam("id") final Integer id) {
        LOGGER.debug("GET request sent from the deleteCurve of the"
                + " CurvePointController to delete CurvePoint {}", id);
        try {
            String deleteCurvePoint = ApiUrlConstants.CURVE_POINT_API_BASE_URL
                    + "/delete/" + id;
            template.exchange(
                    deleteCurvePoint,
                    HttpMethod.DELETE,
                    null,
                    String.class
            );
            LOGGER.info(" CurvePoint {} deleted successfully."
                    + " Redirecting to the CurvePoint list", id);
        } catch (HttpServerErrorException e) {
            LOGGER.error("There is no matching CurvePoint that has id {}", id);
            return "404NotFound/404";
        }
        return REDIRECTION_URL;
    }
}
