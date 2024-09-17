package com.xsurmise.authorizationserver.layers.domain.utilities;

public interface AppEncryptor {
    String encrypt(String info);

    String decrypt(String encrypted);

    String hash(String info);

    boolean matches(String info, String hash);
}
