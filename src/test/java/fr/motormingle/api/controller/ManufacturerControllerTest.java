package fr.motormingle.api.controller;

import fr.motormingle.api.dto.manufacturer.get.ManufacturerItemGet;
import fr.motormingle.api.entity.Manufacturer;
import fr.motormingle.api.exception.NotFoundException;
import fr.motormingle.api.service.ManufacturerService;
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

@WebMvcTest(ManufacturerController.class)
class ManufacturerControllerTest {

    private final String BASE_URL = "/api/v1/manufacturer";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ManufacturerService service;

    @Test
    void getAll() throws Exception {
        List<Manufacturer> manufacturers = List.of(new Manufacturer(), new Manufacturer());
        given(service.findAll()).willReturn(manufacturers);

        mockMvc.perform(get(BASE_URL + "/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getById() throws Exception {
        Manufacturer manufacturer = new Manufacturer();
        given(service.findById(1L)).willReturn(manufacturer);

        mockMvc.perform(get(BASE_URL + "/find/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    void getByIdNotFound() throws Exception {
        given(service.findById(1L)).willThrow(new NotFoundException("Manufacturer with id 1 was not found"));

        mockMvc.perform(get(BASE_URL + "/find/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getManufacturerItemList() throws Exception {
        List<ManufacturerItemGet> manufacturerItemGets = List.of(new ManufacturerItemGet(1L, "Ducati"), new ManufacturerItemGet(2L, "Suzuki"));
        given(service.getManufacturerItemList()).willReturn(manufacturerItemGets);

        mockMvc.perform(get(BASE_URL + "/items")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].*", hasSize(2)))
                .andExpect(jsonPath("$[1].*", hasSize(2)))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Ducati"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].name").value("Suzuki"));

    }
}
