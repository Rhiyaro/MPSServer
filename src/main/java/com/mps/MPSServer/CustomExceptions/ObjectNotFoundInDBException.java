package com.mps.MPSServer.CustomExceptions;

public class ObjectNotFoundInDBException extends Exception{
    public ObjectNotFoundInDBException(String message) {
        super(message);
    }
}
