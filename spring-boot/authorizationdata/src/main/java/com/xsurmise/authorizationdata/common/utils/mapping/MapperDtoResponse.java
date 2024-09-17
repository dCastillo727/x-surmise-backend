package com.xsurmise.authorizationdata.common.utils.mapping;

public interface MapperDtoResponse<D, M> {
    D transform(M domainModel);
}
