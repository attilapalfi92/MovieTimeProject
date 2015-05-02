package com.movietime.exceptions;

/**
 * Created by Attila on 2015-05-02.
 */
public class PersistingFailedException extends Exception {

    public PersistingFailedException(String message) {
        super(message);
    }

    public PersistingFailedException() {
    }
}
