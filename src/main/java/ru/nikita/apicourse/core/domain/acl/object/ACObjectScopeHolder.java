package ru.nikita.apicourse.core.domain.acl.object;

import ru.nikita.apicourse.core.domain.acl.enumeration.ACRoleScope;

public interface ACObjectScopeHolder {
    ACRoleScope getObjectScopeHolder();
    Long getObjectScopeHolderId();
}
