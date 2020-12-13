package com.nnk.poseidon.controllers.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The type HomeController.
 * It provides access to the app home page(accessible by all users)
 * and the Admin home page(accessible only by admins)
 *
 * @author Yahia CHERIFI(corrections and javadoc only)
 */

@Controller
public class HomeController {

    /**
     * Class logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(HomeController.class);
    /**
     * The Poseidon home page.
     *
     * @return an html file
     */
    @GetMapping("/")
    public String home() {
        LOGGER.debug("GET request sent from the HomeController"
                + " to load the home page");
        return "home";
    }

    /**
     * The Poseidon Admin home page.
     *
     * @return the bidList home page(redirection)
     */
    @GetMapping("/admin/home")
    public String adminHome() {
        LOGGER.debug("GET request sent from the HomeController"
                + " to load the admin home page");
        return "redirect:/bidList/list";
    }
}
