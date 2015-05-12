package com.movietime.exceptions;

/**
 * Created by Attila on 2015-05-12.
 */
public class EmailUsedException extends Exception {
    public EmailUsedException(String message) {
        super(message);
    }

    public EmailUsedException() {
    }
}
