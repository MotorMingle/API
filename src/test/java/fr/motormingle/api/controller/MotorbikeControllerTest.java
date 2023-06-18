package fr.motormingle.api.controller;

import fr.motormingle.api.entity.Motorbike;
import fr.motormingle.api.exception.NotFoundException;
import fr.motormingle.api.service.MotorbikeService;
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

@WebMvcTest(MotorbikeController.class)
class MotorbikeControllerTest {

    private final String BASE_URL = "/api/v1/motorbike";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MotorbikeService service;

    @Test
    void getAll() throws Exception {
        List<Motorbike> motorbikes = List.of(new Motorbike(), new Motorbike());
        given(service.findAll()).willReturn(motorbikes);

        mockMvc.perform(get(BASE_URL + "/all").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getById() throws Exception {
        Motorbike motorbike = new Motorbike();
        given(service.findById(1L)).willReturn(motorbike);

        mockMvc.perform(get(BASE_URL + "/find/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    void getByIdNotFound() throws Exception {
        given(service.findById(1L)).willThrow(new NotFoundException("Motorbike with id 1 was not found"));

        mockMvc.perform(get(BASE_URL + "/find/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }
}
