package com.nnk.poseidon.exceptions;

/**
 * ResourceAlreadyExistsException is an exception thrown when trying to save.
 * an object that has the same unique properties of already existing object
 *
 * @author Yahia CHERIFI
 */

public class ResourceAlreadyExistsException extends Exception {

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public ResourceAlreadyExistsException(final String message) {
        super(message);
    }
}
