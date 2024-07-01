package ru.nikita.apicourse.core.service.acl;

import ru.nikita.apicourse.core.domain.acl.enumeration.ACAction;
import ru.nikita.apicourse.core.domain.acl.enumeration.ACObject;
import ru.nikita.apicourse.core.domain.acl.object.ACObjectScopeHolder;
import ru.nikita.apicourse.core.domain.acl.object.ACObjectTarget;
import ru.nikita.apicourse.core.domain.acl.role.entry.EntryRoleTarget;
import ru.nikita.apicourse.core.service.acl.role.DefaultSystemRole;
import ru.nikita.apicourse.core.service.acl.role.Role;

public interface AccessControlService {
    boolean can(EntryRoleTarget roleTarget, ACAction action, ACObjectTarget objectTarget);

    boolean can(EntryRoleTarget roleTarget, ACAction action, ACObject object, ACObjectScopeHolder objectScopeHolder);

    void addRole(EntryRoleTarget roleTarget, Role role);

    void addRole(EntryRoleTarget roleTarget, DefaultSystemRole defaultSystemRole);
}
