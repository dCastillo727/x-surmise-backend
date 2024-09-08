package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.client.jparepository;

import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.client.entity.ClientJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface JpaRepositoryClient extends JpaRepository<ClientJpaEntity, Integer> {
}
