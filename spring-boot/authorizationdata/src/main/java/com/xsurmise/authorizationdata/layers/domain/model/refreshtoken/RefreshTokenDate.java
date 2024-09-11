package com.xsurmise.authorizationdata.layers.domain.model.refreshtoken;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

public record RefreshTokenDate(Instant value, ZoneId zoneId) {
    public RefreshTokenDate {
        if (value == null || zoneId == null) throw new IllegalArgumentException("BlackListTokenDate doesnt accept null values");
    }

    public static RefreshTokenDate from(OffsetDateTime date) {
        return new RefreshTokenDate(
                date.toInstant(),
                date.getOffset()
        );
    }

    public OffsetDateTime toOffsetDateTime() {
        return OffsetDateTime.ofInstant(value, zoneId);
    }

    public static RefreshTokenDate now() {
        return new RefreshTokenDate(Instant.now(), ZoneId.systemDefault());
    }

    public static RefreshTokenDate now(ZoneId zone) {
        return new RefreshTokenDate(Instant.now(), zone);
    }
}
