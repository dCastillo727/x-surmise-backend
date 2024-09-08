package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.blacklisttoken.implementation;

import com.xsurmise.authorizationdata.common.utils.annotations.adapter.DrivenAdapter;
import com.xsurmise.authorizationdata.layers.application.port.driven.persistence.repository.blacklisttoken.BlackListTokenRepositoryDrivenPort;
import com.xsurmise.authorizationdata.layers.domain.model.blacklisttoken.BlackListToken;
import com.xsurmise.authorizationdata.layers.domain.model.blacklisttoken.BlackListTokenId;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.blacklisttoken.jparepository.JpaRepositoryBlackListToken;
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

    @Override
    public void save(BlackListToken blackListToken) {

    }

    @Override
    public Optional<BlackListToken> findByTokenId(BlackListTokenId tokenId) {
        return Optional.empty();
    }

    @Override
    public boolean existsByTokenId(BlackListTokenId tokenId) {
        return false;
    }

    @Override
    public void deleteByTokenId(BlackListTokenId tokenId) {

    }
}
