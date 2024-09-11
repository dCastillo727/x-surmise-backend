package com.xsurmise.authorizationdata.layers.domain.model.appuser;

import java.util.UUID;

public record AppUserId(String value) {
    public AppUserId {
        if (value == null || value.isEmpty()) throw new IllegalArgumentException("AppUserId Value cannot be null or empty");
    }

    public static AppUserId from(String value) {
        return new AppUserId(value);
    }

    public static AppUserId from(UUID value) {
        return new AppUserId(value.toString());
    }

    public static AppUserId generate() {
        UUID uuid = UUID.randomUUID();
        return new AppUserId(uuid.toString());
    }

    public UUID asUUID() {
        return UUID.fromString(this.value);
    }
}
