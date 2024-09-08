package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.refreshtoken.entity;

import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.client.entity.ClientJpaEntity;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.user.entity.AppUserJpaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "oauth_refresh_tokens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenJpaEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "token_id")
    private UUID tokenId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUserJpaEntity appUser;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientJpaEntity client;

    @Column(name = "issued_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime issuedAt;

    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;
}
