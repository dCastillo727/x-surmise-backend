package com.xsurmise.authorizationserver.common.utils.mapping;

public interface MapperDrivenDtoRequest<D, M> {
    D domainToDto(M domain);
}
