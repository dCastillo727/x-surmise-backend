package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.blacklisttoken.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "blacklisted_tokens")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlackListTokenJpaEntity implements Serializable {
    @Id
    @Column(name = "token_id")
    private UUID tokenId;

    @Column(name = "revoked_at", nullable = false)
    @CreationTimestamp
    private OffsetDateTime revokedAt;
}
