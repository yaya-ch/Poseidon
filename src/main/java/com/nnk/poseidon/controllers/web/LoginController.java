package com.nnk.poseidon.controllers.web;

import com.nnk.poseidon.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * The type LoginController.
 * This controller provides some methods that allow users access the login form
 *
 * @author Yahia CHERIFI
 */

@Controller
public class LoginController {

    /**
     * Class logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(LoginController.class);
    /**
     * UserService to inject.
     */
    private final UserService service;

    /**
     * Instantiates a new Login controller.
     *
     * @param userService the userService
     */
    @Autowired
    public LoginController(final UserService userService) {
        this.service = userService;
    }

    /**
     * This method provides access to the html login form.
     *
     * @return the login html form
     */
    @GetMapping("/login")
    public String login() {
        LOGGER.debug("GET request sent from the LoginController"
                + " to load the login form");
        return "login";
    }

    /**
     * This method provides access to the list of all the users(only by admins).
     *
     * @return an html page in which all user are listed
     */
    @GetMapping("/app/secure/article-details")
    public ModelAndView getAllUserArticles() {
        LOGGER.debug("GET request sent from the LoginController"
                + " to get the Users' list");
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", service.findAllUsers());
        mav.setViewName("user/list");
        return mav;
    }

    /**
     * Error model and view.
     *
     * @return the model and view
     */
    @GetMapping("/error")
    public ModelAndView error() {
        ModelAndView mav = new ModelAndView();
        String errorMessage = "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("403");
        return mav;
    }
}
