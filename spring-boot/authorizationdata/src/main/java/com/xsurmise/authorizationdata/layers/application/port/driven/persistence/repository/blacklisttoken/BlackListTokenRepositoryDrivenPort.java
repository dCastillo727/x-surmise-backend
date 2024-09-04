package com.xsurmise.authorizationdata.layers.application.port.driven.persistence.repository.blacklisttoken;

import com.xsurmise.authorizationdata.common.utils.annotations.port.DrivenPort;
import com.xsurmise.authorizationdata.layers.domain.model.blacklisttoken.BlackListToken;
import com.xsurmise.authorizationdata.layers.domain.model.blacklisttoken.BlackListTokenId;

import java.util.Optional;

@DrivenPort
public interface BlackListTokenRepositoryDrivenPort {
    void save(BlackListToken blackListToken);

    Optional<BlackListToken> findByTokenId(BlackListTokenId tokenId);

    boolean existsByTokenId(BlackListTokenId tokenId);

    void deleteByTokenId(BlackListTokenId tokenId);
}
