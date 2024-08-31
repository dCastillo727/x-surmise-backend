package com.xsurmise.authorizationserver.common.utils.command;

import java.time.Instant;

public interface Command {
    static Instant now() {
        return Instant.now();
    }

    CommandId getId();
}
