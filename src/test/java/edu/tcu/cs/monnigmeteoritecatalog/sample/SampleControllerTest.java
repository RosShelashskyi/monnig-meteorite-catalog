package edu.tcu.cs.monnigmeteoritecatalog.sample;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tcu.cs.monnigmeteoritecatalog.system.StatusCode;
import edu.tcu.cs.monnigmeteoritecatalog.utils.IdWorker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
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
        s1.setGroup("H");
        s1.setDate_found_year("1951");
        s1.setSample_weight_g((float)325.1);

        Sample s2 = new Sample();
        s2.setSample_ID("0002");
        s2.setName("Abee");
        s2.setMonnig_number("M499.2");
        s2.setCountry("Canada");
        s2.setSample_class("Enstatite Chondrite");
        s2.setGroup("EH");
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
}
