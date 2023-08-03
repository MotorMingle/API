package fr.motormingle.api.controller;

import fr.motormingle.api.dto.mingler.get.MinglerTagGet;
import fr.motormingle.api.entity.Mingler;
import fr.motormingle.api.exception.NotFoundException;
import fr.motormingle.api.service.MinglerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MinglerController.class)
class MinglerControllerTest {

    private final String BASE_URL = "/api/v1/mingler";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MinglerService service;

    @Test
    void getAll() throws Exception {
        List<Mingler> minglers = List.of(new Mingler("5d4f9dc62d66", "lebarbanchon.valentin@gmail.com", "enixo", "Valentin", "Lebarbanchon"), new Mingler("dedb0e441ff7", "brionne.victor@gmail.com", "vixi9", "Victor", "Brionne"));
        given(service.findAll()).willReturn(minglers);

        mockMvc.perform(get(BASE_URL + "/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getById() throws Exception {
        Mingler mingler = new Mingler("5d4f9dc62d66", "lebarbanchon.valentin@gmail.com", "enixo", "Valentin", "Lebarbanchon");
        given(service.findById("5d4f9dc62d66")).willReturn(mingler);

        mockMvc.perform(get(BASE_URL + "/find/5d4f9dc62d66")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    void getByIdNotFound() throws Exception {
        given(service.findById("v4ch3")).willThrow(new NotFoundException("Mingler with id v4ch3 was not found"));

        mockMvc.perform(get(BASE_URL + "/find/v4ch3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getTagById() throws Exception {
        MinglerTagGet minglerTagGet = new MinglerTagGet("enixo");
        given(service.findTagById("5d4f9dc62d66")).willReturn(minglerTagGet);

        mockMvc.perform(get(BASE_URL + "/5d4f9dc62d66/tag")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.tag").value("enixo"));

    }
}
