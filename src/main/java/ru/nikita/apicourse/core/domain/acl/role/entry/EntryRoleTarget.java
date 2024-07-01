package ru.nikita.apicourse.core.domain.acl.role.entry;

import ru.nikita.apicourse.core.domain.acl.enumeration.ACAction;
import ru.nikita.apicourse.core.domain.acl.enumeration.ACObject;
import ru.nikita.apicourse.core.domain.acl.enumeration.ACRoleScope;
import ru.nikita.apicourse.core.domain.acl.object.ACObjectScopeHolder;

public interface EntryRoleTarget {
    ACRoleScope getEntryRoleTargetScope();

    Long getEntryRoleTargetId();

    EntryRoleTarget getChainRoleTarget();

    default boolean isSystem() {
        return false;
    }

    default boolean can(ACAction action, ACObject object) {
        return this.can(action, object, null);
    }

    default boolean can(ACAction action, ACObject object, ACObjectScopeHolder objectScopeHolder) {
        return false;
    }
}
