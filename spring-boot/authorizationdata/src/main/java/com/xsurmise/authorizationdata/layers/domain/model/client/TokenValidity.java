package com.xsurmise.authorizationdata.layers.domain.model.client;

public record TokenValidity(Integer duration) {
    public TokenValidity {
        if (duration == null) throw new IllegalArgumentException("Duration cannot be null");
    }

    public static TokenValidity of(Integer duration) {
        return new TokenValidity(duration);
    }
}
