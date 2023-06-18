package fr.motormingle.api.controller;

import fr.motormingle.api.entity.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class OidcController {

    @GetMapping("/oidc-principal")
    public User getPrincipal(@AuthenticationPrincipal OidcUser principal) {
        var user = new User();
        user.setEmail(principal.getEmail());
        user.setFirstName(principal.getGivenName());
        user.setLastName(principal.getFamilyName());
        user.setTag(principal.getPreferredUsername());
        user.setBirthDate(LocalDate.parse(principal.getBirthdate()));
        return user;
    }
}
