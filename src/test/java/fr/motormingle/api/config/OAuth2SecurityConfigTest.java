package fr.motormingle.api.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OAuth2SecurityConfigTest {

    @Autowired
    MockMvc mvc;

    @Test
    void unauthenticated() throws Exception {
        mvc.perform(get("/api/v1/car/all"))
                .andExpect(status().isUnauthorized());
    }
}
