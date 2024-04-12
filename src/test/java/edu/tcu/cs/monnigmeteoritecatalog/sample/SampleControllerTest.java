package edu.tcu.cs.monnigmeteoritecatalog.sample;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tcu.cs.monnigmeteoritecatalog.sample.dto.SampleDto;
import edu.tcu.cs.monnigmeteoritecatalog.system.StatusCode;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class SampleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;
    //set up mock objects

    @MockBean
    SampleService sampleService;

    List<Sample> samples;

    @BeforeEach
    void setUp(){
        Sample s1 = new Sample();
        s1.setSample_ID("0001");
        s1.setName("Abbott");
        s1.setMonnig_number("M398.1");
        s1.setCountry("USA");
        s1.setSample_class("Ordinary Chondrite");
        s1.setSample_group("H");
        s1.setDate_found_year("1951");
        s1.setSample_weight_g((float)325.1);

        Sample s2 = new Sample();
        s2.setSample_ID("0002");
        s2.setName("Abee");
        s2.setMonnig_number("M499.2");
        s2.setCountry("Canada");
        s2.setSample_class("Enstatite Chondrite");
        s2.setSample_group("EH");
        s2.setDate_found_year("1952");
        s2.setSample_weight_g((float)453.1);

        this.samples = new ArrayList<>();
        this.samples.add(s1);
        this.samples.add(s2);
    }

    @AfterEach
    void tearDown() { }

    @Test
    void testSampleFindByIdSuccess() throws Exception {
        // Given
        given(this.sampleService.findById("0001")).willReturn(this.samples.getFirst());
        // When and then
        this.mockMvc.perform(get("/samples/0001").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Find One Success"))
                .andExpect(jsonPath("$.data.id").value("0001"))
                .andExpect(jsonPath("$.data.name").value("Abbott"))
                .andExpect(jsonPath("$.data.monnig_number").value("M398.1"))
                .andExpect(jsonPath("$.data.country").value("USA"))
                .andExpect(jsonPath("$.data.sample_class").value("Ordinary Chondrite"))
                .andExpect(jsonPath("$.data.group").value("H"))
                .andExpect(jsonPath("$.data.date_found_year").value("1951"))
                .andExpect(jsonPath("$.data.sample_weight_g").value(325.1F));
    }
    @Test
    void testSampleFindByIdNotFound() throws Exception{
        //Given
        given(this.sampleService.findById("0001")).willThrow(new SampleNotFoundException("0001"));
        // When and then
        this.mockMvc.perform(get("/samples/0001").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(StatusCode.NOT_FOUND))
                .andExpect(jsonPath("$.message").value("Could not find sample with Id 0001 :("))
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void testSampleFindAllSuccess() throws Exception{
        // Given
        given(this.sampleService.findAll()).willReturn(this.samples);
        // When and then
        this.mockMvc.perform(get("/samples").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Find All Success"))
                .andExpect(jsonPath("$.data", Matchers.hasSize(this.samples.size())))
                .andExpect(jsonPath("$.data[0].id").value("0001"))
                .andExpect(jsonPath("$.data[0].name").value("Abbott"))
                .andExpect(jsonPath("$.data[1].id").value("0002"))
                .andExpect(jsonPath("$.data[1].name").value("Abee"));
    }
    @Test
    void testAddSampleSuccess() throws Exception{
        // Given
        SampleDto sampleDto = new SampleDto(null, "Abernathy", "M239.1", "USA", "Ordinary Chondrite", "L", "1941", (float)453.1);
        String json = this.objectMapper.writeValueAsString(sampleDto);

        Sample savedSample = new Sample();
        savedSample.setSample_ID("0003");
        savedSample.setName("Abernathy");
        savedSample.setMonnig_number("M239.1");
        savedSample.setCountry("USA");
        savedSample.setSample_class("Ordinary Chondrite");
        savedSample.setSample_group("L");
        savedSample.setDate_found_year("1941");
        savedSample.setSample_weight_g((float)453.1);

        given(this.sampleService.save(Mockito.any(Sample.class))).willReturn(savedSample);

        // When and then
        this.mockMvc.perform(post("/samples").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Add Success"))
                .andExpect(jsonPath("$.data.id").isNotEmpty())
                .andExpect(jsonPath("$.data.name").value(savedSample.getName()))
                .andExpect(jsonPath("$.data.monnig_number").value(savedSample.getMonnig_number()))
                .andExpect(jsonPath("$.data.country").value(savedSample.getCountry()))
                .andExpect(jsonPath("$.data.sample_class").value(savedSample.getSample_class()))
                .andExpect(jsonPath("$.data.group").value(savedSample.getSample_group()))
                .andExpect(jsonPath("$.data.date_found_year").value(savedSample.getDate_found_year()))
                .andExpect(jsonPath("$.data.sample_weight_g").value(savedSample.getSample_weight_g()));
    }
    @Test
    void testUpdateSampleSuccess() throws Exception {
        SampleDto sampleDto = new SampleDto("0001", "Abbott", "New Monnig Num", "USA", "Ordinary Chondrite", "H", "1951", (float)325.1);
        String json = this.objectMapper.writeValueAsString(sampleDto);
        Sample updatedSample = new Sample();
        updatedSample.setSample_ID("0001");
        updatedSample.setName("Abbott");
        updatedSample.setMonnig_number("New Monnig Num");
        updatedSample.setCountry("USA");
        updatedSample.setSample_class("Ordinary Chondrite");
        updatedSample.setSample_group("H");
        updatedSample.setDate_found_year("1951");
        updatedSample.setSample_weight_g((float)325.1);


        // Given
        given(this.sampleService.update(eq("0001"), Mockito.any(Sample.class))).willReturn(updatedSample);

        // When and then
        this.mockMvc.perform(put("/samples/0001").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Update Success"))
                .andExpect(jsonPath("$.data.id").value("0001"))
                .andExpect(jsonPath("$.data.name").value(updatedSample.getName()))
                .andExpect(jsonPath("$.data.monnig_number").value(updatedSample.getMonnig_number()))
                .andExpect(jsonPath("$.data.country").value(updatedSample.getCountry()))
                .andExpect(jsonPath("$.data.sample_class").value(updatedSample.getSample_class()))
                .andExpect(jsonPath("$.data.group").value(updatedSample.getSample_group()))
                .andExpect(jsonPath("$.data.date_found_year").value(updatedSample.getDate_found_year()))
                .andExpect(jsonPath("$.data.sample_weight_g").value(updatedSample.getSample_weight_g()));
    }
    @Test
    void testUpdateSampleErrorWithNonExistentId() throws Exception {
        SampleDto sampleDto = new SampleDto("0001", "Abbott", "New Monnig Num", "USA", "Ordinary Chondrite", "H", "1951", (float)325.1);
        String json = this.objectMapper.writeValueAsString(sampleDto);

        // Given
        given(this.sampleService.update(eq("0001"), Mockito.any(Sample.class))).willThrow(new SampleNotFoundException("0001"));

        // When and then
        this.mockMvc.perform(put("/samples/0001").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(StatusCode.NOT_FOUND))
                .andExpect(jsonPath("$.message").value("Could not find sample with Id 0001 :("))
                .andExpect(jsonPath("$.data").isEmpty());
    }
    @Test
    void testDeleteSampleSuccess() throws Exception {
        doNothing().when(this.sampleService).delete("0001");

        this.mockMvc.perform(delete("/samples/0001").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Delete Success"))
                .andExpect(jsonPath("$.data").isEmpty());
    }
    @Test
    void testDeleteSampleErrorWithNonExistentId() throws Exception {
        doThrow(new SampleNotFoundException("0001")).when(this.sampleService).delete("0001");
        this.mockMvc.perform(delete("/samples/0001").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(StatusCode.NOT_FOUND))
                .andExpect(jsonPath("$.message").value("Could not find sample with Id 0001 :("))
                .andExpect(jsonPath("$.data").isEmpty());

    }




}
