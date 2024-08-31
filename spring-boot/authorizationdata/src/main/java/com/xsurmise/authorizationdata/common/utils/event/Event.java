package com.xsurmise.authorizationdata.common.utils.event;

import com.xsurmise.authorizationserver.common.utils.message.AppMessage;

import java.time.Instant;

public interface Event extends AppMessage {
    static Instant now() {
        return Instant.now();
    }

    EventId getId();
}
