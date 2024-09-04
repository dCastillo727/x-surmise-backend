package com.xsurmise.authorizationdata.common.utils.mapping;

import com.xsurmise.authorizationdata.common.utils.command.Command;

public interface MapperDto<D, M> {
    D toDto(M domainModel);

    M toDomainModel(D dto);

    <T extends Command> T toCommandFromDto(D dto, Class<T> commandClass);

    <T extends Command> T toCommandFromModel(M domainModel, Class<T> commandClass);
}
