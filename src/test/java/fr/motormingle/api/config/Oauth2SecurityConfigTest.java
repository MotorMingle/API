package fr.motormingle.api.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class Oauth2SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenUnauthenticatedUserThenRedirectToLogin() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser
    void whenAuthenticatedUserThenOk() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }
}
