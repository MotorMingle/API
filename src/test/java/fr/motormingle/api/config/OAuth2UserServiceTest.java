package fr.motormingle.api.config;

import fr.motormingle.api.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

class OAuth2UserServiceTest {

    @Test
    void testOidcUserService() {
        var userService = Mockito.mock(UserService.class);
        var userRequest = Mockito.mock(OidcUserRequest.class);
        var oidcUser = Mockito.mock(OidcUser.class);
        var delegate = Mockito.mock(OidcUserService.class);
        when(delegate.loadUser(userRequest)).thenReturn(oidcUser);

        var config = new Oauth2SecurityConfig();

        var result = config.oidcUserService().loadUser(userRequest);

        var captor = ArgumentCaptor.forClass(OidcUser.class);
        verify(userService, times(1)).registerUser(captor.capture());

        var capturedUser = captor.getValue();
        assertSame(result, capturedUser, "The registered user should be the same as the returned user");
    }
}
