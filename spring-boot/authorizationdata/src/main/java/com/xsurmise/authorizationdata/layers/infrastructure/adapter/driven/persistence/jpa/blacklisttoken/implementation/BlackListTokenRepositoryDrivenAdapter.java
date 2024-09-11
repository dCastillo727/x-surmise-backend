package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.blacklisttoken.implementation;

import com.xsurmise.authorizationdata.common.utils.annotations.adapter.DrivenAdapter;
import com.xsurmise.authorizationdata.common.utils.mapping.MapperEntity;
import com.xsurmise.authorizationdata.layers.application.port.driven.persistence.repository.blacklisttoken.BlackListTokenRepositoryDrivenPort;
import com.xsurmise.authorizationdata.layers.domain.model.blacklisttoken.BlackListToken;
import com.xsurmise.authorizationdata.layers.domain.model.blacklisttoken.BlackListTokenId;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.blacklisttoken.entity.BlackListTokenJpaEntity;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.blacklisttoken.jparepository.JpaRepositoryBlackListToken;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(isolation = Isolation.READ_COMMITTED)
@DrivenAdapter
@RequiredArgsConstructor
public class BlackListTokenRepositoryDrivenAdapter implements BlackListTokenRepositoryDrivenPort {
    private final JpaRepositoryBlackListToken jpaRepositoryBlackListToken;

    private final MapperEntity<BlackListToken, BlackListTokenJpaEntity> blackListTokenMapper;

    @Override
    public void save(BlackListToken blackListToken) {
        if (blackListToken == null)
            throw new NullPointerException("blackListToken cannot be null");

        if (jpaRepositoryBlackListToken.existsById(blackListToken.getTokenId().asUUID()))
            throw new EntityExistsException();

        BlackListTokenJpaEntity jpaEntity = blackListTokenMapper.toEntity(blackListToken);

        jpaRepositoryBlackListToken.save(jpaEntity);
    }

    @Override
    public Optional<BlackListToken> findByTokenId(BlackListTokenId tokenId) {
        if (tokenId == null) throw new NullPointerException("tokenId cannot be null");

        return jpaRepositoryBlackListToken.findById(tokenId.asUUID()).map(blackListTokenMapper::toDomainModel);
    }

    @Override
    public boolean existsByTokenId(BlackListTokenId tokenId) {
        if (tokenId == null) throw new NullPointerException("tokenId cannot be null");

        return jpaRepositoryBlackListToken.existsById(tokenId.asUUID());
    }

    @Override
    public void deleteByTokenId(BlackListTokenId tokenId) {
        if (tokenId == null) throw new NullPointerException("tokenId cannot be null");

        if (!jpaRepositoryBlackListToken.existsById(tokenId.asUUID()))
            throw new EntityNotFoundException();

        jpaRepositoryBlackListToken.deleteById(tokenId.asUUID());
    }
}
