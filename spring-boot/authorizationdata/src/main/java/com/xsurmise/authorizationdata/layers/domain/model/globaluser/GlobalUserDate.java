package com.xsurmise.authorizationdata.layers.domain.model.globaluser;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

public record GlobalUserDate(Instant value, ZoneId zoneId) {
    public GlobalUserDate {
        if (value == null || zoneId == null)
            throw new IllegalArgumentException("Global user date instant or zoneId cannot be null");
    }

    public static GlobalUserDate from(OffsetDateTime date) {
        return new GlobalUserDate(date.toInstant(), ZoneId.systemDefault());
    }

    public OffsetDateTime toOffsetDateTime() {
        return OffsetDateTime.ofInstant(value, zoneId);
    }

    public static GlobalUserDate now() {
        return new GlobalUserDate(Instant.now(), ZoneId.systemDefault());
    }

    public static GlobalUserDate now(ZoneId zone) {
        return new GlobalUserDate(Instant.now(), zone);
    }
}
