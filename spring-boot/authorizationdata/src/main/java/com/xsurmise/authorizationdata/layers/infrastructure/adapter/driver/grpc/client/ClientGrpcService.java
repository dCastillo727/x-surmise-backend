package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driver.grpc.client;

import com.xsurmise.authorizationdata.*;
import com.xsurmise.authorizationdata.common.utils.mapping.MapperDtoRequest;
import com.xsurmise.authorizationdata.common.utils.mapping.MapperDtoResponse;
import com.xsurmise.authorizationdata.layers.application.core.client.usecase.ClientCreateUseCase;
import com.xsurmise.authorizationdata.layers.application.core.client.usecase.ClientGetUseCases;
import com.xsurmise.authorizationdata.layers.domain.command.client.CreateClientCommand;
import com.xsurmise.authorizationdata.layers.domain.model.client.*;
import com.xsurmise.authorizationdata.layers.domain.model.client.Client;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@GrpcService
@RequiredArgsConstructor
public class ClientGrpcService extends ClientServiceGrpc.ClientServiceImplBase {
    private final ClientGetUseCases clientGetUseCases;
    private final ClientCreateUseCase clientCreateUseCase;

    private final MapperDtoResponse<ClientResponse, Client> clientResponseMapper;
    private final MapperDtoRequest<ClientCreateRequest, Client> clientCreateRequestMapper;

    @Override
    public void getClients(EmptyRequest request, StreamObserver<ClientListResponse> responseObserver) {
        List<Client> clients = clientGetUseCases.getAllClients();

        ClientListResponse.Builder responseBuilder = ClientListResponse.newBuilder();
        for (Client client : clients) {
            responseBuilder.addData(clientResponseMapper.transform(client));
        }

        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

    @Override
    public void getClientById(ClientIdRequest request, StreamObserver<ClientResponse> responseObserver) {
        Optional<Client> client = clientGetUseCases.getClientBy(ClientId.from(request.getClientId()));

        if (client.isEmpty()) {
            responseObserver.onError(Status.NOT_FOUND.asException());
            return;
        }

        responseObserver.onNext(clientResponseMapper.transform(client.get()));
        responseObserver.onCompleted();
    }

    @Override
    public void getClientByUuid(ClientUuidRequest request, StreamObserver<ClientResponse> responseObserver) {
        UUID uuid;
        try {
            uuid = UUID.fromString(request.getId());
        } catch (IllegalArgumentException e) {
            responseObserver.onError(Status.INVALID_ARGUMENT.asException());
            return;
        }

        Optional<Client> client = clientGetUseCases.getClientBy(ClientSimpleId.from(uuid));
        if (client.isEmpty()) {
            responseObserver.onError(Status.NOT_FOUND.asException());
            return;
        }

        responseObserver.onNext(clientResponseMapper.transform(client.get()));
        responseObserver.onCompleted();
    }

    @Override
    public void createClient(ClientCreateRequest request, StreamObserver<ClientUuidResponse> responseObserver) {
        CreateClientCommand command = clientCreateRequestMapper.toCommandFromDto(request, CreateClientCommand.class);
        Client client = clientCreateUseCase.createClient(command);

        responseObserver.onNext(ClientUuidResponse.newBuilder().setId(client.getId().id()).build());
        responseObserver.onCompleted();
    }
}
