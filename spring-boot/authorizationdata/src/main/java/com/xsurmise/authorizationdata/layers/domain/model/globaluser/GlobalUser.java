package com.xsurmise.authorizationdata.layers.domain.model.globaluser;

import com.xsurmise.authorizationdata.common.utils.aggregate.AggregateDomainEvent;
import com.xsurmise.authorizationdata.layers.domain.event.globaluser.GlobalUserDomainEvent;
import com.xsurmise.authorizationdata.layers.domain.model.globaluser.role.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class GlobalUser implements AggregateDomainEvent {
    private final List<GlobalUserDomainEvent> events;
    private final GlobalUserId id;
    private final GlobalUserDate createdAt;
    private GlobalUserEmailAddress email;
    private PhoneNumber phone;
    private GlobalUserDate updatedAt;
    private UserRole role;
    private GlobalUserDate lastLogInAt;

    public GlobalUser(
            GlobalUserId id,
            GlobalUserDate createdAt,
            GlobalUserEmailAddress email,
            PhoneNumber phone,
            GlobalUserDate updatedAt,
            UserRole role,
            GlobalUserDate lastLogInAt
    ) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(createdAt);
        Objects.requireNonNull(email);
        Objects.requireNonNull(phone);
        Objects.requireNonNull(updatedAt);
        Objects.requireNonNull(role);
        Objects.requireNonNull(lastLogInAt);

        this.events = new ArrayList<>();
        this.id = id;
        this.createdAt = createdAt;
        this.email = email;
        this.phone = phone;
        this.updatedAt = updatedAt;
        this.role = role;
        this.lastLogInAt = lastLogInAt;
    }

    @Override
    public void clearEvents() {
        events.clear();
    }

    @Override
    public List<GlobalUserDomainEvent> listEvents() {
        return List.copyOf(events);
    }
}
