package com.xsurmise.authorizationserver.common.utils.exceptions.app;

public class IllegalPortAdapterTypesCombinationException extends RuntimeException {
    public IllegalPortAdapterTypesCombinationException(String className) {
        super("Error on class " + className + ": Adapters should match type with their port interface, only Driven-Driven and Driver-Driver " +
                "annotation combinations are allowed");
    }
}
