package edu.tcu.cs.monnigmeteoritecatalog.loan;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tcu.cs.monnigmeteoritecatalog.sample.Sample;
import edu.tcu.cs.monnigmeteoritecatalog.sample.SampleNotFoundException;
import edu.tcu.cs.monnigmeteoritecatalog.system.StatusCode;
import edu.tcu.cs.monnigmeteoritecatalog.utils.IdWorker;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class LoanControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Mock
    IdWorker idWorker;

    @MockBean
    LoanService loanService;

    List<Loan> loans;

    @BeforeEach
    void setUp(){
        Sample s1 = new Sample();
        s1.setSample_ID(idWorker.nextId() + "");
        s1.setName("Abbott");
        s1.setMonnig_number("M398.1");
        s1.setCountry("USA");
        s1.setSample_class("Ordinary Chondrite");
        s1.setSample_group("H");
        s1.setDate_found_year("1951");
        s1.setSample_weight_g((float)325.1);

        Sample s2 = new Sample();
        s2.setSample_ID(idWorker.nextId() + "");
        s2.setName("Abee");
        s2.setMonnig_number("M499.2");
        s2.setCountry("Canada");
        s2.setSample_class("Enstatite Chondrite");
        s2.setSample_group("EH");
        s2.setDate_found_year("1952");
        s2.setSample_weight_g((float)453.1);

        Loan l1 = new Loan();
        l1.setLoan_ID("0001");
        l1.setLoanee_name("John Doe");
        l1.setLoanee_email("johndoe@example.com");
        l1.setLoanee_institution("Meteorite Research Institute");
        l1.setLoanee_address("123 Meteorite Street, Cityville, USA");
        l1.setLoan_start_date("2024-04-01 10:00:00");
        l1.setLoan_due_date("2024-04-15 10:00:00");
        l1.setLoan_notes("Fragile meteorite, handle with care.");
        l1.setSamples_on_loan(Arrays.asList(s1));

        Loan l2 = new Loan();
        l2.setLoan_ID("0002");
        l2.setLoanee_name("Jane Smith");
        l2.setLoanee_email("janesmith@example.com");
        l2.setLoanee_institution("Space Exploration Society");
        l2.setLoanee_address("456 Galaxy Avenue, Star City, Canada");
        l2.setLoan_start_date("2024-03-20 09:30:00");
        l2.setLoan_due_date("2024-04-10 09:30:00");
        l2.setLoan_notes("Rare meteorite, research project.");
        l2.setSamples_on_loan(Arrays.asList(s2));

        this.loans = new ArrayList<>();
        this.loans.add(l1);
        this.loans.add(l2);
    }

    @AfterEach
    void tearDown(){ }

    @Test
    void testLoanFindByIdSuccess() throws Exception{
        //given
        given(this.loanService.findById("0001")).willReturn(this.loans.getFirst());

        //when and then
        this.mockMvc.perform(get("/loan/0001").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Find One Success"))
                .andExpect(jsonPath("$.data.id").value("0001"))
                .andExpect(jsonPath("$.data.loanee_name").value("John Doe"))
                .andExpect(jsonPath("$.data.loanee_email").value("jonhdoe@example.com"))
                .andExpect(jsonPath("$.data.loanee_insitution").value("Meteorite Research Institute"))
                .andExpect(jsonPath("$.data.loanee_address").value("123 Meteorite Street, Cityville, USA"))
                .andExpect(jsonPath("$.data.loan_start_date").value("2024-04-01 10:00:00"))
                .andExpect(jsonPath("$.data.loan_due_date").value("2024-04-15 10:00:00"))
                .andExpect(jsonPath("$.data.loan_notes").value("Fragile meteorite, handle with care."));
    }

    @Test
    void testLoanFindByIdNotFound() throws Exception{
        //given
        given(this.loanService.findById("0001")).willThrow(new SampleNotFoundException("0001"));
        //when and then
        this.mockMvc.perform(get("/loan/0001").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(StatusCode.NOT_FOUND))
                .andExpect(jsonPath("$.message").value("Could not find loan with Id 0001"))
                .andExpect(jsonPath("$.data.id").isEmpty());
    }

    @Test
    void testLoanFindAllSuccess() throws Exception{
        //given
        given(this.loanService.findAll()).willReturn(this.loans);
        //when and then
        this.mockMvc.perform(get("/loan").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Find One Success"))
                .andExpect(jsonPath("$.data", Matchers.hasSize(this.loans.size())))
                .andExpect(jsonPath("$.data[0].id").value("0001"))
                .andExpect(jsonPath("$.data[1].id").value("0002"))
                .andExpect(jsonPath("$.date[0].loanee_name").value("John Doe"))
                .andExpect(jsonPath("$.date[1].loanee_name").value("Jane Smith"));
    }
}
