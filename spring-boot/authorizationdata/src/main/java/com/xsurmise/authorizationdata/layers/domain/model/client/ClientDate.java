package com.xsurmise.authorizationdata.layers.domain.model.client;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

public record ClientDate(Instant value, ZoneId zoneId) {
    public static ClientDate from(OffsetDateTime date) {
        return new ClientDate(
                date.toInstant(),
                date.getOffset()
        );
    }

    public OffsetDateTime toOffsetDateTime() {
        return OffsetDateTime.ofInstant(value, zoneId);
    }

    public static ClientDate now() {
        return new ClientDate(Instant.now(), ZoneId.systemDefault());
    }

    public static ClientDate now(ZoneId zone) {
        return new ClientDate(Instant.now(), zone);
    }
}
