package com.xsurmise.authorizationdata.layers.domain.model.globaluser;

public record PhoneNumber(String value) {
    public PhoneNumber {
        if (value == null || value.isEmpty()) throw new IllegalArgumentException("Phone number cannot be null or empty");
    }

    public static PhoneNumber from(String value) {
        return new PhoneNumber(value);
    }
}
