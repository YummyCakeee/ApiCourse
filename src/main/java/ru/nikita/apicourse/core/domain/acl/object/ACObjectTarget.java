package ru.nikita.apicourse.core.domain.acl.object;

import ru.nikita.apicourse.core.domain.acl.enumeration.ACObject;

public interface ACObjectTarget {
    ACObject getACObjectType();
    ACObjectScopeHolder getACObjectScopeHolder();
}
