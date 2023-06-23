package fr.motormingle.api.service;

import fr.motormingle.api.entity.Mingler;
import fr.motormingle.api.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;

import java.time.Instant;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    private OidcUser oidcUser;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        OidcUserAuthority oidcUserAuthority = new OidcUserAuthority(
                new OidcIdToken(
                        "eyJhbGciOiJSUzI1NiIsImtpZCI6IjA1MTUwYTEzMjBiOTM5NWIwNTcxNjg3NzM3NjkyODUwOWJhYjQ0YWMiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiI0MDc0MDg3MTgxOTIuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiI0MDc0MDg3MTgxOTIuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMTc4NTIwMzU2MTM3NzgzMjcyMDgiLCJlbWFpbCI6InZpY3Rvci5icmlvbm5lQGdtYWlsLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJhdF9oYXNoIjoiWnJob3M2bU9hR0Zfdlc5NDlZU0lsZyIsIm5hbWUiOiJWaWN0b3IgQlJJT05ORSIsInBpY3R1cmUiOiJodHRwczovL2xoMy5nb29nbGV1c2VyY29udGVudC5jb20vYS9BQWNIVHRmNkdUS1I1aklETEVEbmNVZWsxQ1FrZ0ZpaVg3NkpJR3VVQlI2Qz1zOTYtYyIsImdpdmVuX25hbWUiOiJWaWN0b3IiLCJmYW1pbHlfbmFtZSI6IkJSSU9OTkUiLCJsb2NhbGUiOiJmciIsImlhdCI6MTY4NzQ2OTgyNiwiZXhwIjoxNjg3NDczNDI2fQ.qhJdEfjthxieCsnDZolA_ey31e9DlKn-4GLQ2rrLBjy2e8gxzJuT4bYCmBPGuv0QQWZj7SbJNhg6z5x3uQ599otYZPuPdlYTh_rPK1zqKuSmJfTe5_qn1IYWcyBhMCp2uSRhukhrv7kxEdwUGQy0jbT6gnzvN1RUjBFLVYQFDvMQVgUtBVh6BJDMq9F2mvonvgjRDvN_GuT-Yq6rGav2DawptZ6l7g-GlCHtYe_mvnc2nOo7ZHdKLDaQbqrL1uQcCEC3oPHKaF51R8Cq7CrSYSE-45ooL_tn2jyWMvpsiGauOixAbVZESriHdr4uxrc_Tf8yEZC8cEkF2Q7c5T1FYQ",
                        Instant.now(),
                        Instant.now().plusSeconds(60),
                        Map.of("scope", "openid https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile",
                                "sub", "117852035613778327208")));
        oidcUser = new DefaultOidcUser(Collections.singleton(oidcUserAuthority), oidcUserAuthority.getIdToken());
    }

    @Test
    void testRegisterNewUser() {
        when(userRepository.findByEmailIgnoreCase(anyString())).thenReturn(Optional.empty());

        userService.registerUser(oidcUser);
        assertNotNull(userRepository.findByEmailIgnoreCase(oidcUser.getEmail()));
    }

    @Test
    void testRegisterUser() {
        var mingler = new Mingler();
        mingler.setEmail(oidcUser.getEmail());
        when(userRepository.findByEmailIgnoreCase(anyString())).thenReturn(Optional.of(mingler));

        userService.registerUser(oidcUser);
        assertNotNull(userRepository.findByEmailIgnoreCase(oidcUser.getEmail()));
    }

}
