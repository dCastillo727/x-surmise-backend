package com.xsurmise.authorizationserver.common.utils.mapping;

public interface MapperDrivenDtoResponse<D, M> {
    M responseToDomain(D dto);
}
