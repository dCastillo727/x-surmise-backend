package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driver.grpc.client;

import com.xsurmise.authorizationdata.ClientListResponse;
import com.xsurmise.authorizationdata.ClientResponse;
import com.xsurmise.authorizationdata.ClientServiceGrpc;
import com.xsurmise.authorizationdata.EmptyRequest;
import com.xsurmise.authorizationdata.layers.application.core.client.service.ClientService;
import com.xsurmise.authorizationdata.layers.domain.model.client.Client;
import com.xsurmise.authorizationdata.layers.domain.model.client.GrantType;
import com.xsurmise.authorizationdata.layers.domain.model.client.RedirectUri;
import com.xsurmise.authorizationdata.layers.domain.model.client.Scope;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;

@GrpcService
@RequiredArgsConstructor
public class ClientGrpcService extends ClientServiceGrpc.ClientServiceImplBase {
    private final ClientService clientService;

    @Override
    public void getClients(EmptyRequest request, StreamObserver<ClientListResponse> responseObserver) {
        List<Client> clients = clientService.getAllClients();

        ClientListResponse.Builder responseBuilder = ClientListResponse.newBuilder();
        for (Client client : clients) {
            responseBuilder.addData(
                    ClientResponse.newBuilder()
                            .setClientId(client.getClientId().value())
                            .setClientSecret(client.getClientSecret().value())
                            .setAccessTokenValidity(client.getAccessTokenValidity().duration())
                            .setRefreshTokenValidity(client.getRefreshTokenValidity().duration())
                            .addAllGrantTypes(
                                    client.getGrantTypes().stream().map(GrantType::getValue).toList()
                            )
                            .addAllRedirectUris(
                                    client.getRedirectUris().stream().map(RedirectUri::uri).toList()
                            )
                            .addAllScopes(
                                    client.getScopes().stream().map(Scope::getValue).toList()
                            )
            );
        }

        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }
}
