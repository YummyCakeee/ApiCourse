package ru.nikita.apicourse.core.domain.acl.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ACRoleScope {
    GLOBAL(0),
    BLOG(1),
    BLOG_POST(2);

    private final int code;
}
