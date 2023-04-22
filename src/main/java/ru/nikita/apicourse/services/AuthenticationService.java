package ru.nikita.apicourse.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nikita.apicourse.exceptions.AuthenticationException;
import ru.nikita.apicourse.exceptions.RegistrationException;
import ru.nikita.apicourse.http.request.AuthenticateRequest;
import ru.nikita.apicourse.http.request.RegisterRequest;
import ru.nikita.apicourse.models.Role;
import ru.nikita.apicourse.models.User;
import ru.nikita.apicourse.repositories.UserRepository;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${cookie.token.secret}")
    private String secretKey;

    public void register(RegisterRequest registerRequest) throws RegistrationException {


        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent())
            throw new RegistrationException("This email is already taken");
        User user = User.builder()
                .firstName(registerRequest.getFirstName())
                .secondName(registerRequest.getSecondName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.ROLE_USER)
                .build();
        userRepository.save(user);
    }

    public User authenticate(AuthenticateRequest authenticateRequest) {
         User user = userRepository.findByEmail(authenticateRequest.getEmail()).orElseThrow(
                 () -> new UsernameNotFoundException("User " + authenticateRequest.getEmail() + " not found")
         );
         if (!passwordEncoder.matches(authenticateRequest.getPassword(), user.getPassword())) {
             throw new AuthenticationException("Invalid password");
         }
         return user;
    }

    public User findByToken(String token) {
        try {
            String[] tokenParts = token.split("&");
            Long userId = Long.valueOf(tokenParts[0]);
            String userEmail = tokenParts[1];
            String hmac = tokenParts[2];

            User user = userRepository.findByEmail(userEmail).orElse(null);

            if (Objects.isNull(user) || !user.getId().equals(userId) || !hmac.equals(calculateHmac(user))) {
                throw new Exception();
            }
            return user;
        } catch (Exception ex) {
            return null;
        }
    }

    public String generateToken(User user) {
        return String.format("%d&%s&%s", user.getId(), user.getEmail(), calculateHmac(user));
    }

    private String calculateHmac(@NonNull User user) {
        byte[] secretKeyBytes = Objects.requireNonNull(secretKey).getBytes(StandardCharsets.UTF_8);
        byte[] valueBytes = (user.getId() + "&" + user.getEmail()).getBytes(StandardCharsets.UTF_8);
        try {
            Mac mac = Mac.getInstance("HmacSHA512");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyBytes, "HmacSHA512");
            mac.init(secretKeySpec);
            return Base64.getEncoder().encodeToString(mac.doFinal(valueBytes));
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}
