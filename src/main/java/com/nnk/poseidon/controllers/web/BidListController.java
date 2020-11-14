package com.nnk.poseidon.controllers.web;

import com.nnk.poseidon.converters.BidListConverter;
import com.nnk.poseidon.domain.BidList;
import com.nnk.poseidon.dto.BidListDTO;
import com.nnk.poseidon.services.BidListService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * The type Bid list controller.
 * This controller provide web services that allow CRUD operations on BidList
 *
 * @author Yahia CHERIFI
 */

@Controller
@RequestMapping("/bidList")
public class BidListController {

    /**
     * Class logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(BidListController.class);
    /**
     * BidListService to inject.
     */
    private final BidListService service;

    /**
     * BidListConverter to inject.
     */
    private final BidListConverter converter;

    /**
     * Instantiates a new Bid list controller.
     *
     * @param bidListService the bid list service
     * @param bidListConverter the BidListConverter
     */
    @Autowired
    public BidListController(final BidListService bidListService,
                             final BidListConverter bidListConverter) {
        this.service = bidListService;
        this.converter = bidListConverter;
    }

    /**
     * Home string.
     *
     * @param model the model
     * @return an html page that lists all the bidLists
     */
    @GetMapping("/list")
    public String home(final Model model) {
        LOGGER.debug("GET request sent from the bidListController"
                + " to load all the BidLists");
        List<BidListDTO> bidListDTOList = service.findAll();
        model.addAttribute("bidListList", bidListDTOList);
        return "bidList/list";
    }

    /**
     * Add bid form string.
     *
     * @param model the model
     * @return an html form that allows adding a new bidList
     */
    @GetMapping("/add")
    public String addBidForm(final Model model) {
        LOGGER.debug("GET request sent from the BidListController"
                + " to display the add BidList form");
        BidListDTO bidListToSave = new BidListDTO();
        model.addAttribute("bidList", bidListToSave);
        return "bidList/add";
    }

    /**
     * Validate and save a new bidList.
     *
     * @param bidListDTO    the bidList to save
     * @param result the result
     * @param model  the model
     * @return the string
     */
    @PostMapping("/validate")
    public String validate(@Valid final BidListDTO bidListDTO,
                           final BindingResult result,
                           final Model model) {
        LOGGER.debug("POST request sent from the BidLIstController"
                + " to save a new BidList");
        if (!result.hasFieldErrors()) {
            bidListDTO.setBidListDate(
                    new Timestamp(System.currentTimeMillis()));
            bidListDTO.setCreationDate(
                    new Timestamp(System.currentTimeMillis()));
            bidListDTO.setRevisionName(null);
            bidListDTO.setRevisionDate(null);
            BidList bidListToSave =
                    converter.bidListDTOToBidListEntity(bidListDTO);
            service.save(bidListToSave);
            model.addAttribute("bidListList", service.findAll());
            return "redirect:/bidList/list";
        }
        model.addAttribute("bidList", bidListDTO);
        return "bidList/add";
    }

    /**
     * Show update form string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     */
    @GetMapping("/update")
    public String showUpdateForm(@RequestParam("id") final Integer id,
                                 final Model model) {
        LOGGER.debug("GET request sent from the BidListController"
                + " to display the update form");
        Optional<BidListDTO> bidListToUpdate = service.findBidListById(id);
        model.addAttribute("bidList", bidListToUpdate.get());
        return "bidList/update";
    }

    /**
     * Update bid string.
     *
     * @param id      the id
     * @param bidList the bid list
     * @param result  the result
     * @param model   the model
     * @return the string
     */
    @PostMapping("/update/{id}")
    public String updateBid(@PathVariable("id") final Integer id,
                            @Valid final BidListDTO bidList,
                            final BindingResult result,
                            final Model model) {
        LOGGER.debug("POST request sent to from the BidListController"
                + " to update the BidList {}", id);
        if (!result.hasFieldErrors()) {
            bidList.setRevisionDate(new Timestamp(System.currentTimeMillis()));
            BidList bidListToUpdate =
                    converter.bidListDTOToBidListEntity(bidList);
            service.updateBidList(id, bidListToUpdate);
            model.addAttribute("bidListList", service.findAll());
            return "redirect:/bidList/list";
        }
        model.addAttribute("bidList", bidList);
        return "bidList/update";
    }

    /**
     * Delete bid string.
     *
     * @param id the id
     * @return the string
     */
    @GetMapping("/delete")
    public String deleteBid(@RequestParam("id") final Integer id) {
        LOGGER.debug("DELETE request sent from the BidListController"
                + " to delete BidList {}", id);
        service.deleteById(id);
        return "redirect:/bidList/list";
    }
}
