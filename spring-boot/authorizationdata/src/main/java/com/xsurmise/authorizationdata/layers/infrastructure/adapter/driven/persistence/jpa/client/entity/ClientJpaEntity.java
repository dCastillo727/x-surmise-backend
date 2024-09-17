package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.client.entity;

import com.xsurmise.authorizationdata.layers.infrastructure.utils.jpa.CustomStringArrayType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "oauth_clients")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientJpaEntity implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "client_id", unique = true, nullable = false)
    private String clientId;

    @Column(name = "client_secret", unique = true, nullable = false)
    private String clientSecret;

    @Column(name = "grant_types", nullable = false, columnDefinition = "varchar[]")
    @Type(value = CustomStringArrayType.class)
    private String[] grantTypes;

    @Column(name = "redirect_uris", columnDefinition = "varchar[]")
    @Type(value = CustomStringArrayType.class)
    private String[] redirectUris;

    @Column(name = "post_logout_uris", columnDefinition = "varchar[]")
    @Type(value = CustomStringArrayType.class)
    private String[] postLogoutUris;

    @Column(name = "scopes", nullable = false, columnDefinition = "varchar[]")
    @Type(value = CustomStringArrayType.class)
    private String[] scopes;

    @Column(name = "access_token_validity", nullable = false)
    private int accessTokenValidity;

    @Column(name = "refresh_token_validity", nullable = false)
    private int refreshTokenValidity;

    @Column(name = "created_at")
    @CreationTimestamp
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private OffsetDateTime updatedAt;
}
