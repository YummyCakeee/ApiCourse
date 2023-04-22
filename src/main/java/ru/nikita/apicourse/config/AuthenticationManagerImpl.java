package ru.nikita.apicourse.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class AuthenticationManagerImpl extends ProviderManager {

    @Autowired
    public AuthenticationManagerImpl(AuthenticationProvider authenticationProvider) {
        super(Collections.singletonList(authenticationProvider));
    }
}
