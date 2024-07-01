package ru.nikita.apicourse.core.service.acl.role;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum DefaultSystemRole {
    ADMINISTRATOR(1L),
    USER(2L);

    private final Long roleId;
}
