package com.xsurmise.authorizationdata.layers.domain.model.blacklisttoken;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

public record BlackListTokenDate(Instant value, ZoneId zoneId) {
    public BlackListTokenDate {
        if (value == null || zoneId == null) throw new IllegalArgumentException("BlackListTokenDate doesnt accept null values");
    }

    public static BlackListTokenDate from(OffsetDateTime date) {
        return new BlackListTokenDate(
                date.toInstant(),
                date.getOffset()
        );
    }

    public OffsetDateTime toOffsetDateTime() {
        return OffsetDateTime.ofInstant(value, zoneId);
    }

    public static BlackListTokenDate now() {
        return new BlackListTokenDate(Instant.now(), ZoneId.systemDefault());
    }

    public static BlackListTokenDate now(ZoneId zone) {
        return new BlackListTokenDate(Instant.now(), zone);
    }
}
