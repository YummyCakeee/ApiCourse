package ru.nikita.apicourse.core.domain.acl.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ACAction {
    FULL_CONTROL(0),
    READ(1),
    EDIT(2),
    ADD(3),
    DELETE(4);

    private final int code;
}
