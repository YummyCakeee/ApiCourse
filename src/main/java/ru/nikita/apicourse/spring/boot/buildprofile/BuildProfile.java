package ru.nikita.apicourse.spring.boot.buildprofile;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Getter
@Component
public class BuildProfile {
    private final Environment environment;
    private Profile profile;

    public BuildProfile(Environment environment) {
        this.environment = environment;
        String activeProfile = environment.getActiveProfiles()[0];
        profile = Arrays.stream(Profile.values())
                .filter(p -> p.name().equalsIgnoreCase(activeProfile))
                .findFirst().orElse(Profile.PRODUCTION);

        log.info("BUILD PROFILE: {}", profile);
    }

    public boolean isLocal() {
        return profile.equals(Profile.LOCAL);
    }

    public boolean isTest() {
        return profile.equals(Profile.TEST);
    }

    public boolean isDevelopment() {
        return profile.equals(Profile.LOCAL) || profile.equals(Profile.TEST);
    }

    public boolean isProduction() {
        return profile.equals(Profile.PRODUCTION);
    }

    public boolean is(Profile profile) {
        return this.profile.equals(profile);
    }

    public enum Profile {
        LOCAL,
        TEST,
        PRODUCTION;
    }
}
