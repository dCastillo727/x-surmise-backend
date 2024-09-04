package com.xsurmise.authorizationdata.common.utils.aggregate;

import com.xsurmise.authorizationdata.common.utils.event.DomainEvent;

import java.util.List;

public interface AggregateDomainEvent {
    void clearEvents();

    List<? extends DomainEvent> listEvents();
}
