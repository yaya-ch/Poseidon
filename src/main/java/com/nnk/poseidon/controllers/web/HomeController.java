package com.nnk.poseidon.controllers.web;

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
     * The Poseidon home page.
     *
     * @return an html file
     */
    @GetMapping("/")
    public String home() {
        return "home";
    }

    /**
     * The Poseidon Admin home page.
     *
     * @return the bidList home page(redirection)
     */
    @GetMapping("/admin/home")
    public String adminHome() {
        return "redirect:/bidList/list";
    }
}
