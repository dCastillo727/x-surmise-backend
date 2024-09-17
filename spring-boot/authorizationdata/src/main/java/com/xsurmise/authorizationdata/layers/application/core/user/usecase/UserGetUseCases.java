package com.xsurmise.authorizationdata.layers.application.core.user.usecase;

import com.xsurmise.authorizationdata.layers.domain.model.appuser.AppUser;
import com.xsurmise.authorizationdata.layers.domain.model.appuser.AppUserId;
import com.xsurmise.authorizationdata.layers.domain.model.globaluser.GlobalUserId;

public interface UserGetUseCases {
    AppUser getUserById(AppUserId id);

    AppUser getUserById(GlobalUserId id);

    AppUser getUserByUsername(String username);

    AppUser getUserByEmail(String email);
}
