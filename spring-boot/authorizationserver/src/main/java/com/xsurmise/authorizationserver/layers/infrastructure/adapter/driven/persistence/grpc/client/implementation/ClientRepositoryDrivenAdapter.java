package com.xsurmise.authorizationserver.layers.infrastructure.adapter.driven.persistence.grpc.client.implementation;

import com.xsurmise.authorizationserver.*;
import com.xsurmise.authorizationserver.common.utils.annotations.adapter.DrivenAdapter;
import com.xsurmise.authorizationserver.common.utils.mapping.MapperDrivenDtoRequest;
import com.xsurmise.authorizationserver.common.utils.mapping.MapperDrivenDtoResponse;
import com.xsurmise.authorizationserver.layers.application.port.driven.persistence.repository.client.ClientRepositoryDrivenPort;
import com.xsurmise.authorizationserver.layers.domain.model.client.Client;
import com.xsurmise.authorizationserver.layers.domain.model.client.ClientId;
import com.xsurmise.authorizationserver.layers.domain.model.client.ClientSimpleId;
import com.xsurmise.authorizationserver.layers.infrastructure.adapter.driven.persistence.grpc.client.mapping.ClientGrpcRequestMapper;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(isolation = Isolation.READ_COMMITTED)
@DrivenAdapter
@RequiredArgsConstructor
public class ClientRepositoryDrivenAdapter implements ClientRepositoryDrivenPort {
    private final ClientGrpcRequestMapper clientRequestMapper;
    @GrpcClient("client-service")
    private ClientServiceGrpc.ClientServiceBlockingStub clientService;

    private final MapperDrivenDtoResponse<ClientResponse, Client> responseMapper;
    private final MapperDrivenDtoRequest<ClientCreateRequest, Client> requestMapper;

    @Override
    public List<Client> getClients() {
        EmptyRequest request = EmptyRequest.newBuilder().build();

        ClientListResponse response = clientService.getClients(request);

        response.getDataList().forEach(System.out::println);
        return List.of();
    }

    @Override
    public Optional<Client> getClientBy(ClientId clientId) {
        if (clientId == null) throw new NullPointerException("clientId is null");

        ClientIdRequest request = ClientIdRequest.newBuilder().setClientId(clientId.value()).build();

        try {
            ClientResponse response = clientService.getClientById(request);
            System.out.println(response);

            return Optional.of(responseMapper.responseToDomain(response));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Client> getClientBy(ClientSimpleId id) {
        if (id == null) throw new NullPointerException("id is null");

        ClientUuidRequest request = ClientUuidRequest.newBuilder().setId(id.value()).build();

        try {
            ClientResponse response = clientService.getClientByUuid(request);
            System.out.println(response);

            return Optional.of(responseMapper.responseToDomain(response));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public ClientSimpleId saveClient(Client client) {
        if (client == null) throw new NullPointerException("client is null");

        ClientCreateRequest request = clientRequestMapper.domainToDto(client);

        ClientUuidResponse response = clientService.createClient(request);

        //TODO replace with logger
        System.out.println("created " + response);
        return ClientSimpleId.from(response.getId());
    }
}
