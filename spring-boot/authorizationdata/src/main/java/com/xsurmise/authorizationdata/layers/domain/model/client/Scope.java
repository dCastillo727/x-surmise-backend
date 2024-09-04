package com.xsurmise.authorizationdata.layers.domain.model.client;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Scope {
    READ("read"),
    WRITE("write"),
    ADMIN("admin")
    ;

    private final String value;
}
