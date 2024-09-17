package com.xsurmise.authorizationdata.common.utils.mapping;

import com.xsurmise.authorizationdata.common.utils.command.Command;

public interface MapperDtoRequest<D, M> {
    M toDomainModel(D dto);

    <T extends Command> T toCommandFromDto(D dto, Class<T> commandClass);
}
