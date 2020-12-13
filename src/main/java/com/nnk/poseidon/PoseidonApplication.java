package com.nnk.poseidon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application main class.
 * used to start the application
 *
 * @author Yahia CHERIFI
 */

@SpringBootApplication
public class PoseidonApplication {

    /**
     * Main method.
     * @param args arg.
     */
    public static void main(final String[] args) {
        SpringApplication.run(PoseidonApplication.class, args);
    }

    /**
     * Class constructor.
     */
    protected PoseidonApplication() {
    }
}
