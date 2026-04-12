package com.CreativityStudios.Exceptions;

public class NotAcceptedQueryException extends Exception {

    @Override
    public synchronized String getMessage() {
        return "Endpoint URI is valid, but contains queries not accepted at this endpoint";
    }
}
