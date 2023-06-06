package com.example.songsappYOTPO.producer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProducerController.class)
class ProducerControllerTestIT {
    @MockBean
    private ProducerService producerService;

    @Autowired
    private MockMvc mockMvc;

    private static Producer producer1;
    private static Producer producer2;
    private final static Long ID1 = 15L;
    private final static Long ID2 = 15L;
    private final static String FIRST_NAME1 = "Shai";
    private final static String FIRST_NAME2 = "Uzi";
    private final static String LAST_NAME1 = "Edri";
    private final static String LAST_NAME2 = "Cohen";

    @BeforeEach
    void setUp() {
        producer1 = Producer.builder().id(ID1).firstName(FIRST_NAME1).lastName(LAST_NAME1).build();
        producer2 = Producer.builder().id(ID2).firstName(FIRST_NAME2).lastName(LAST_NAME2).build();
    }

    @Test
    void getProducers() throws Exception {
        // given
        List<Producer> mockProducers = new ArrayList<>();
        mockProducers.add(producer1);
        mockProducers.add(producer2);
        // when
        when(producerService.getProducers()).thenReturn(mockProducers);
        // then
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/producer/"))
                .andExpect(status().isOk())
                .andReturn();
        String responseContent = result.getResponse().getContentAsString();
        List<Producer> producers = new ObjectMapper().readValue(responseContent,
                new TypeReference<List<Producer>>() {
                });
        assertEquals(producers.size(), 2);
        //assertNotEquals(producers.size(), 2);
    }

    @Test
    void deleteById() throws Exception {
        doNothing().when(producerService).deleteById(anyLong());
        mockMvc.perform(delete("/producer/" + ID1))
                .andExpect(status().isOk());
        verify(producerService, times(1)).deleteById(ID1);
    }

    @Test
    void getProducerById() throws Exception {
        //given
        Optional<Producer> producerGetById = Optional.of(producer1);
        // when
        when(producerService.findById(anyLong())).thenReturn(producerGetById);
        MvcResult mvcResult = mockMvc.perform(get("/producer/" + ID1))
                .andExpect(status().isOk())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        Producer found = new ObjectMapper().readValue(content,
                new TypeReference<Producer>() {
                });
        //then
        assertEquals(found.getId(), ID1);
        verify(producerService, times(1)).findById(ID1);
    }


    @Test
    void findByLastNameAndFirstNameAllIgnoreCase() throws Exception {
        //given
        List<Producer> mockProducers = List.of(producer1);
        //when
        when(producerService
                .findByLastNameAndFirstNameAllIgnoreCase(anyString(), anyString()))
                .thenReturn(mockProducers);
        //then
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/producer/getbyfullname")
                        .param("lastName", LAST_NAME1)
                        .param("firstName", FIRST_NAME1)
                )
                .andExpect(status().isOk())
                .andReturn();
        String responseContent = result.getResponse().getContentAsString();
        List<Producer> producers = new ObjectMapper().readValue(responseContent,
                new TypeReference<List<Producer>>() {
                });
        assertEquals(producers.size(), 1);
        assertEquals(producers.get(0).getId(), ID1);
    }

    @Test
    void addProducer() throws Exception {
        // given
        // when
        when(producerService.save(any())).thenReturn(producer1);
        ObjectMapper objectMapper = new ObjectMapper();
        String requestContent = objectMapper.writeValueAsString(producer1);
        //then
        MvcResult result = mockMvc.perform(post("/producer/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andExpect(status().isOk())
                .andReturn();
        String responseContent = result.getResponse().getContentAsString();
        Producer savedProducer = objectMapper.readValue(responseContent,
                new TypeReference<Producer>() {
                });
        assertNotNull(savedProducer);
        assertEquals(savedProducer, producer1);
    }

//    @Test
//    void updateProducerByIdLoadObject() {
//    }
//
//    @Test
//    void updateProducerByIdNoLoadObject() {
//    }
}