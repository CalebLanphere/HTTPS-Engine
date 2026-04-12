package com.CreativityStudios.Exceptions;

public class IncorrectEndpointException extends Exception{
    @Override
    public String getMessage() {
        return "Endpoint requested in HTTPRequest does not match current endpoint";
    }
}
