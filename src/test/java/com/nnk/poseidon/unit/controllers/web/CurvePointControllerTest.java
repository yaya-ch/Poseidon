package com.nnk.poseidon.unit.controllers.web;

import com.nnk.poseidon.converters.CurvePointConverter;
import com.nnk.poseidon.domain.CurvePoint;
import com.nnk.poseidon.dto.CurvePointDTO;
import com.nnk.poseidon.services.CurvePointService;
import com.nnk.poseidon.unit.DataLoader;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Yahia CHERIFI
 */

@Tag("ViewControllers")
@DisplayName("CurvePoint view controller")
@AutoConfigureWebMvc
@SpringBootTest
class CurvePointControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @MockBean
    private CurvePointService service;

    @MockBean
    private CurvePointConverter converter;

    private CurvePointDTO curvePointDTO;

    private CurvePoint curvePoint;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        DataLoader dataLoader = new DataLoader();
        curvePointDTO = dataLoader.setCurvePointDTO();
        curvePoint = dataLoader.setCurvePoint();
    }

    @DisplayName("Load all CurvePoints")
    @Test
    void home_shouldReturnCurvePointHomePage_andAllCurvePoints() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/curvePoint/list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("curvePointList"))
                .andExpect(view().name("curvePoint/list"))
                .andExpect(status().isOk());
        verify(service, times(1)).findAllCurvePoints();
    }

    @DisplayName("Load add CurvePont form")
    @Test
    void addCurvePointForm_shouldLoadTheAddForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/curvePoint/add"))
                .andExpect(MockMvcResultMatchers
                        .model().attributeExists("curvePoint"))
                .andExpect(view().name("curvePoint/add"))
                .andExpect(status().isOk());
    }

    @DisplayName("POST: Save new CurvePoint successfully")
    @Test
    void givenValidCurvePoint_whenSavingNewCurvePoint_thenResponseShouldBeOk_andCurvePageShouldBeLoaded() throws Exception {
        when(converter.curvePointDTOToCurvePointEntity(any(CurvePointDTO.class))).thenReturn(curvePoint);
        curvePointDTO.setCurvePointId(1);
        mockMvc.perform(MockMvcRequestBuilders.post("/curvePoint/validate")
                .contentType(MediaType.TEXT_HTML)
                .param("curveId", curvePoint.getCurveId().toString())
                .param("term", curvePoint.getTerm().toString())
                .param("value", curvePoint.getValue().toString()))
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(redirectedUrl("/curvePoint/list"))
                .andReturn();
    }

    @DisplayName("POST: invalid CurvePoint information return the add form")
    @Test
    void givenInvalidCurvePoint_whenSavingNewCurvePoint_thenAddFormShouldBeReloaded() throws Exception {
        when(converter.curvePointDTOToCurvePointEntity(any(CurvePointDTO.class))).thenReturn(curvePoint);
        mockMvc.perform(MockMvcRequestBuilders.post("/curvePoint/validate")
                .contentType(MediaType.TEXT_HTML)
                .param("curveId", curvePoint.getCurveId().toString())
                .param("value", "")
                .param("term", curvePoint.getTerm().toString()))
                .andExpect(MockMvcResultMatchers.model().hasErrors())
                .andExpect(view().name("curvePoint/add"))
                .andReturn();
    }

    @DisplayName("UPDATING CurvePoint: Valid CurvePointId returns the update form")
    @Test
    void showUpdateForm() throws Exception {
        when(service.findCurvePointById(anyInt())).thenReturn(Optional.of(curvePointDTO));
        mockMvc.perform(MockMvcRequestBuilders.get("/curvePoint/update?id=1"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("curvePoint"))
                .andExpect(view().name("curvePoint/update"))
                .andExpect(status().isOk()).andReturn();
    }

    @DisplayName("UPDATE CurvePoint: Invalid CurvePoint id returns the error page")
    @Test
    void givenInvalidCurvePointId_whenShowUpdateForm_then404ErrorPageShouldBeReturned() throws Exception {
        when(service.findCurvePointById(anyInt())).thenThrow(new NoSuchElementException());
        mockMvc.perform(MockMvcRequestBuilders.get("/curvePoint/update?id=1"))
                .andExpect(view().name("404NotFound/404"))
                .andReturn();
    }

    @DisplayName("UPDATE CurvePoint successfully")
    @Test
    void givenValidCurvePoint_whenUpdateCurvePoint_thenRequestShouldBeRedirectedToCurveHomePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/curvePoint/update/1")
                .contentType(MediaType.TEXT_HTML)
                .param("curveId", curvePoint.getCurveId().toString())
                .param("term", curvePoint.getTerm().toString())
                .param("value", curvePoint.getValue().toString()))
                .andExpect(model().hasNoErrors())
                .andExpect(redirectedUrl("/curvePoint/list"))
                .andReturn();
    }

    @DisplayName("UPDATING with wrong CurvePoint information returns the UPDATE form")
    @Test
    void givenInvalidCurvePoint_whenUpdateCurvePoint_thenUpdateFormShouldBeReloaded() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/curvePoint/update/1")
                .contentType(MediaType.TEXT_HTML)
                .param("curveId", "")
                .param("term", curvePoint.getTerm().toString())
                .param("value", curvePoint.getValue().toString()))
                .andExpect(model().hasErrors())
                .andExpect(MockMvcResultMatchers.view().name("curvePoint/update"))
                .andReturn();
    }

    @DisplayName("DELETE CurvePoint successfully")
    @Test
    void givenValidCurvePointId_whenDeleteCurvePoint_thenRequestShouldBeDirectedTOCurveHomePage() throws Exception {
        when(service.findCurvePointById(anyInt())).thenReturn(Optional.of(curvePointDTO));
        mockMvc.perform(MockMvcRequestBuilders.get("/curvePoint/delete?id=1"))
                .andExpect(redirectedUrl("/curvePoint/list"));
        verify(service, times(1)).deleteCurvePointById(1);
    }

    @DisplayName("DELETE CurvePoint redirect to 404 not found page")
    @Test
    void givenInvalidCurvePointId_whenDeleteCurvePoint_then404ErrorPageShouldBeReturned() throws Exception {
        when(service.findCurvePointById(anyInt())).thenThrow(new NoSuchElementException());
        mockMvc.perform(MockMvcRequestBuilders.get("/curvePoint/delete?id=1"))
                .andExpect(view().name("404NotFound/404"))
                .andReturn();
        verify(service, never()).deleteCurvePointById(1);
    }
}