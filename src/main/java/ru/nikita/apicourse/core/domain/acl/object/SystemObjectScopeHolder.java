package ru.nikita.apicourse.core.domain.acl.object;

import ru.nikita.apicourse.core.domain.acl.enumeration.ACRoleScope;

public enum SystemObjectScopeHolder implements ACObjectScopeHolder{
    INSTANCE;

    @Override
    public ACRoleScope getObjectScopeHolder() {
        return ACRoleScope.GLOBAL;
    }

    @Override
    public Long getObjectScopeHolderId() {
        return null;
    }
}
