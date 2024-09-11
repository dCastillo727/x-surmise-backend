package com.xsurmise.authorizationdata.layers.domain.model.appuser;

public record AppUserEmailAddress(String value) {
    public AppUserEmailAddress {
        if (value == null || value.isEmpty()) throw new IllegalArgumentException("Email address cannot be null or empty");
    }

    public static AppUserEmailAddress from(String value) {
        return new AppUserEmailAddress(value);
    }
}
