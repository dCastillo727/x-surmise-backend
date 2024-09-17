package com.xsurmise.authorizationserver.layers.application.core.client.usecase;

import com.xsurmise.authorizationserver.layers.domain.command.client.ClientCreateCommand;
import com.xsurmise.authorizationserver.layers.domain.model.client.Client;

public interface ClientCreateUseCase {
    Client createClient(ClientCreateCommand client);
}
