package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.client.entity;

import com.xsurmise.authorizationdata.layers.infrastructure.utils.jpa.CustomStringArrayType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "oauth_clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientJpaEntity implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

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

    @Column(name = "scopes", nullable = false, columnDefinition = "varchar[]")
    @Type(value = CustomStringArrayType.class)
    private String[] scopes;

    @Column(name = "access_token_validity", nullable = false)
    private int accessTokenValidity;

    @Column(name = "refresh_token_validity", nullable = false)
    private int refreshTokenValidity;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
