package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.refreshtoken.entity;

import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.client.entity.ClientJpaEntity;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.user.entity.AppUserJpaEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "oauth_refresh_tokens")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenJpaEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "token_id")
    private UUID tokenId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    private AppUserJpaEntity appUser;

    @Column(name = "user_id")
    private UUID userId;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false, insertable = false, updatable = false)
    private ClientJpaEntity client;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "issued_at", nullable = false)
    @CreationTimestamp
    private OffsetDateTime issuedAt;

    @Column(name = "expires_at", nullable = false)
    private OffsetDateTime expiresAt;
}
