package ru.nikita.apicourse.core.domain.auth.type;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum AuthStrategyType {
    BEARER((short)0, "Bearer");

    private final short code;
    private final String shortName;

    public static AuthStrategyType fromShortName(String shortName) {
        return Arrays.stream(values()).filter(type -> type.getShortName().equals(shortName)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Could not find strategy type with short name " + shortName));
    }

    public static AuthStrategyType fromCode(short code) {
        return Arrays.stream(values()).filter(type -> type.getCode() == code).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Could not find strategy type with code " + code));
    }
}
