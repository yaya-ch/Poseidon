package com.nnk.poseidon.unit.controllers.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.poseidon.converters.CurvePointConverter;
import com.nnk.poseidon.domain.CurvePoint;
import com.nnk.poseidon.services.CurvePointService;
import com.nnk.poseidon.unit.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Yahia CHERIFI
 */

@Tag("ApiControllers")
@DisplayName("CurvePointApiController unit tests")
@AutoConfigureWebMvc
@SpringBootTest
class CurvePointApiControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @MockBean
    private CurvePointService service;

    @MockBean
    private CurvePointConverter converter;

    private CurvePoint curvePoint;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        DataLoader dataLoader = new DataLoader();
        curvePoint = dataLoader.setCurvePoint();
    }

    @DisplayName("Save CurvePoint successfully")
    @Test
    void givenCurvePoint_whenSave_theResponseShouldBeOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/curvePoint/add")
                .content(new ObjectMapper().writeValueAsString(curvePoint))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @DisplayName("Save invalid CurvePoint throws Exception(Bad request)")
    @Test
    void givenCurvePoint_whenSave_thenExceptionShouldBeThrown() throws Exception {
        curvePoint.setValue(null);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/curvePoint/add")
                .content(new ObjectMapper().writeValueAsString(curvePoint))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("Update CurvePoint successfully")
    @Test
    void givenValidCurvePointId_whenUpdate_thenResponseShouldBeOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/curvePoint/update/1")
                .content(new ObjectMapper().writeValueAsString(curvePoint))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @DisplayName("Update CurvePoint throws an exception")
    @Test
    void givenInvalidCurvePoint_whenUpdate_thenExceptionShouldBeThrown() throws Exception {
        curvePoint.setValue(null);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/curvePoint/update/1")
                .content(new ObjectMapper().writeValueAsString(curvePoint))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("Retrieve all CurvePoints from database")
    @Test
    void findAll_shouldReturnCorrectResponse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/curvePoint/findAll"))
                .andExpect(status().isOk());
    }

    @DisplayName("Find CurvePoint by Id")
    @Test
    void givenCorrectId_whenFindCurvePointById_thenResponseShouldBeOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/curvePoint/findById/1")
                .content(new ObjectMapper().writeValueAsString(curvePoint))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @DisplayName("Delete CurvePoint by Id")
    @Test
    void givenValidCurvePointId_whenDeleteCurvePointById_thenResponseShouldBeOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/curvePoint/delete/1"))
                .andExpect(status().isOk());
    }
}