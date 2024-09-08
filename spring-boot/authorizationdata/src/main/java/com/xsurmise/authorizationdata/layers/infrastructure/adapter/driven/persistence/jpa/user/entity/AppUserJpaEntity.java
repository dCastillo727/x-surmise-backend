package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.user.entity;

import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.client.entity.ClientJpaEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "app_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppUserJpaEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "global_user_id", nullable = false)
    private GlobalUserJpaEntity globalUser;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientJpaEntity client;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "encrypted_password", nullable = false)
    private String encryptedPassword;

    @Column(name = "is_linked", nullable = false)
    private Boolean isLinked;
}
