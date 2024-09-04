package com.xsurmise.authorizationdata.common.utils.mapping;

public interface MapperEntity<M, E> {
    E toEntity(M domainModel);

    M toDomainModel(E entity);
}
