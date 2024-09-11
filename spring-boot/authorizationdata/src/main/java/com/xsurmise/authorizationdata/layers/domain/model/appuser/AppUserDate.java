package com.xsurmise.authorizationdata.layers.domain.model.appuser;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

public record AppUserDate(Instant value, ZoneId zoneId) {
    public AppUserDate {
        if (value == null || zoneId == null) throw new IllegalArgumentException("Value and zoneId cannot be null");
    }

    public static AppUserDate from(OffsetDateTime date) {
        return new AppUserDate(date.toInstant(), ZoneId.systemDefault());
    }

    public OffsetDateTime toOffsetDateTime() {
        return OffsetDateTime.ofInstant(value, ZoneId.systemDefault());
    }

    public static AppUserDate now() {
        return new AppUserDate(Instant.now(), ZoneId.systemDefault());
    }

    public static AppUserDate now(ZoneId zone) {
        return new AppUserDate(Instant.now(), zone);
    }
}
