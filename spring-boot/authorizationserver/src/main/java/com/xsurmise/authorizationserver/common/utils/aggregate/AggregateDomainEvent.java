package com.xsurmise.authorizationserver.common.utils.aggregate;

import com.xsurmise.authorizationserver.common.utils.event.DomainEvent;

import java.util.List;

public interface AggregateDomainEvent {
    void clearEvents();

    List<? extends DomainEvent> listEvents();
}
