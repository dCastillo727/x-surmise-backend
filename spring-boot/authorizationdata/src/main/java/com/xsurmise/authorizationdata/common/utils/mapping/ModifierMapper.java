package com.xsurmise.authorizationdata.common.utils.mapping;

public interface ModifierMapper<O, E> {
    <T extends ModifierMapper<O, E>> T applyChangesFrom(final O origin);

    E to(final E end);
}
