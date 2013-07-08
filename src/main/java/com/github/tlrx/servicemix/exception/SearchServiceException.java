package com.github.tlrx.servicemix.exception;

/**
 * Main exception class used by Search Service.
 */
public class SearchServiceException extends RuntimeException {

    public SearchServiceException(String message) {
        super(message);
    }

    public SearchServiceException() {
    }
}
