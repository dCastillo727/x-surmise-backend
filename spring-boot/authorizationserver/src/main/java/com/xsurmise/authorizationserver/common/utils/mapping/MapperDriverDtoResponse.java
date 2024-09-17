package com.xsurmise.authorizationserver.common.utils.mapping;

public interface MapperDriverDtoResponse<D, M> {
    D transform(M domainModel);
}
