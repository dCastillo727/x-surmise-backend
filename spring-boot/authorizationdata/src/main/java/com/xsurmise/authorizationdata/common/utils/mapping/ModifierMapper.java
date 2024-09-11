package com.xsurmise.authorizationdata.common.utils.mapping;

public interface ModifierMapper<O, E> {
    ModifierMapper<O, E> applyChangesFrom(final O origin);

    E to(final E end);
}
