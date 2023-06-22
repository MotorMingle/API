package fr.motormingle.api.service;

import fr.motormingle.api.entity.Mingler;
import fr.motormingle.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Tries to find a new user in the database, if not found, creates it
     *
     * @param oidcUser the user to save
     */
    public void registerUser(OidcUser oidcUser) {
        userRepository.findByEmailIgnoreCase(oidcUser.getEmail()).ifPresentOrElse(
                user -> {
                },
                () -> {
                    var user = new Mingler();
                    user.setId(UUID.randomUUID());
                    user.setEmail(oidcUser.getEmail());
                    user.setFirstName(oidcUser.getGivenName());
                    user.setLastName(oidcUser.getFamilyName());
                    user.setTag(oidcUser.getPreferredUsername());
                    if (oidcUser.getBirthdate() != null) {
                        user.setBirthDate(LocalDate.parse(oidcUser.getBirthdate()));
                    }
                    userRepository.save(user);
                }
        );
    }
}
