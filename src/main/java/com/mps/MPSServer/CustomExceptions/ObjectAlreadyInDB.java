package com.mps.MPSServer.CustomExceptions;

public class ObjectAlreadyInDB extends Exception {
    public ObjectAlreadyInDB(String message) {
        super(message);
    }
}
