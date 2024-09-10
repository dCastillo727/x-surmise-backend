package com.xsurmise.authorizationdata.layers.application.core.client.usecase;

import com.xsurmise.authorizationdata.layers.domain.model.client.Client;

import java.util.List;

public interface ClientGetAllUseCase {
    List<Client> getAllClients();
}
