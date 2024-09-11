package com.xsurmise.authorizationdata.layers.domain.model.accesstoken;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

public record AccessTokenDate(Instant value, ZoneId zoneId) {
    public AccessTokenDate {
        if (value == null || zoneId == null) throw new IllegalArgumentException("BlackListTokenDate doesnt accept null values");
    }

    public static AccessTokenDate from(OffsetDateTime date) {
        return new AccessTokenDate(
                date.toInstant(),
                date.getOffset()
        );
    }

    public OffsetDateTime toOffsetDateTime() {
        return OffsetDateTime.ofInstant(value, zoneId);
    }

    public static AccessTokenDate now() {
        return new AccessTokenDate(Instant.now(), ZoneId.systemDefault());
    }

    public static AccessTokenDate now(ZoneId zone) {
        return new AccessTokenDate(Instant.now(), zone);
    }
}
