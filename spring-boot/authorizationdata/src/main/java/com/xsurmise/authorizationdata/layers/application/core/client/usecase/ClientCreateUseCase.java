package com.xsurmise.authorizationdata.layers.application.core.client.usecase;

import com.xsurmise.authorizationdata.layers.domain.command.client.CreateClientCommand;
import com.xsurmise.authorizationdata.layers.domain.model.client.Client;

public interface ClientCreateUseCase {
    Client createClient(CreateClientCommand command);
}
