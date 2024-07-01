package ru.nikita.apicourse.api.authentication;

import ru.nikita.apicourse.core.domain.acl.enumeration.ACAction;
import ru.nikita.apicourse.core.domain.acl.enumeration.ACObject;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permission {
    ACAction action();
    ACObject object();
}
