package com.xsurmise.authorizationdata.layers.domain.model.globaluser;

import java.util.regex.Pattern;

public record GlobalUserEmailAddress(String value) {
    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\" +
                    ".[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public GlobalUserEmailAddress {
        if (value == null || value.isEmpty()) throw new IllegalArgumentException("Email address cannot be null or empty");

        if (!EMAIL_PATTERN.matcher(value).matches()) throw new IllegalArgumentException("Invalid email address");
    }

    public static GlobalUserEmailAddress from(final String value) {
        return new GlobalUserEmailAddress(value);
    }

    private boolean isValid(final String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }
}
