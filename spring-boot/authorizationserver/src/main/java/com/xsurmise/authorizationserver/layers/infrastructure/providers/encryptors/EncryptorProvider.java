package com.xsurmise.authorizationserver.layers.infrastructure.providers.encryptors;

import com.xsurmise.authorizationserver.layers.domain.utilities.AppEncryptor;

public class EncryptorProvider implements AppEncryptor {
    @Override
    public String encrypt(String info) {
        return "";
    }

    @Override
    public String decrypt(String encrypted) {
        return "";
    }

    @Override
    public String hash(String info) {
        return "";
    }

    @Override
    public boolean matches(String info, String hash) {
        return false;
    }
}
