package edu.tcu.cs.monnigmeteoritecatalog.loan;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tcu.cs.monnigmeteoritecatalog.loan.dto.LoanDto;
import edu.tcu.cs.monnigmeteoritecatalog.sample.Sample;
import edu.tcu.cs.monnigmeteoritecatalog.system.StatusCode;
import edu.tcu.cs.monnigmeteoritecatalog.system.exception.ObjectNotFoundException;
import edu.tcu.cs.monnigmeteoritecatalog.utils.IdWorker;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles(value = "dev")
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
        s1.setSample_ID("0003");
        s1.setName("Abbott");
        s1.setMonnig_number("M398.1");
        s1.setCountry("USA");
        s1.setSample_class("Ordinary Chondrite");
        s1.setSample_group("H");
        s1.setDate_found_year("1951");
        s1.setSample_weight_g((float)325.1);

        Sample s2 = new Sample();
        s2.setSample_ID("0004");
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

        Loan l2 = new Loan();
        l2.setLoan_ID("0002");
        l2.setLoanee_name("Jane Smith");
        l2.setLoanee_email("janesmith@example.com");
        l2.setLoanee_institution("Space Exploration Society");
        l2.setLoanee_address("456 Galaxy Avenue, Star City, Canada");
        l2.setLoan_start_date("2024-03-20 09:30:00");
        l2.setLoan_due_date("2024-04-10 09:30:00");
        l2.setLoan_notes("Rare meteorite, research project.");

        Loan l3 = new Loan();
        l3.setLoan_ID("0003");
        l3.setLoanee_name("Carson Freeman");
        l3.setLoanee_email("janesmith@example.com");
        l3.setLoanee_institution("Space Exploration Society");
        l3.setLoanee_address("456 Galaxy Avenue, Star City, Canada");
        l3.setLoan_start_date("2024-03-20 09:30:00");
        l3.setLoan_due_date("2024-04-10 09:30:00");
        l3.setLoan_notes("Rare meteorite, research project.");

        this.loans = new ArrayList<>();
        this.loans.add(l1);
        this.loans.add(l2);
    }

    @AfterEach
    void tearDown(){ }

    @Test
    void testLoanFindByIdSuccess() throws Exception{
        //given
        given(this.loanService.findById("0001")).willReturn(this.loans.get(0));

        //when and then
        this.mockMvc.perform(get("/api/loan/view/0001").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Find One Success"))
                .andExpect(jsonPath("$.data.id").value("0001"))
                .andExpect(jsonPath("$.data.loanee_name").value("John Doe"))
                .andExpect(jsonPath("$.data.loanee_email").value("johndoe@example.com"))
                .andExpect(jsonPath("$.data.loanee_institution").value("Meteorite Research Institute"))
                .andExpect(jsonPath("$.data.loanee_address").value("123 Meteorite Street, Cityville, USA"))
                .andExpect(jsonPath("$.data.loan_start_date").value("2024-04-01 10:00:00"))
                .andExpect(jsonPath("$.data.loan_due_date").value("2024-04-15 10:00:00"))
                .andExpect(jsonPath("$.data.loan_notes").value("Fragile meteorite, handle with care."));
    }

    @Test
    void testLoanFindByIdNotFound() throws Exception{
        //given
        given(this.loanService.findById("0001")).willThrow(new ObjectNotFoundException("loan", "0001"));
        //when and then
        this.mockMvc.perform(get("/api/loan/view/0001").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(StatusCode.NOT_FOUND))
                .andExpect(jsonPath("$.message").value("Could not find loan with Id 0001"))
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void testLoanFindAllSuccess() throws Exception{
        //given
        given(this.loanService.findAll()).willReturn(this.loans);
        //when and then
        this.mockMvc.perform(get("/api/loan/all").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Find All Success"))
                .andExpect(jsonPath("$.data", Matchers.hasSize(this.loans.size())))
                .andExpect(jsonPath("$.data[0].id").value("0001"))
                .andExpect(jsonPath("$.data[1].id").value("0002"))
                .andExpect(jsonPath("$.data[0].loanee_name").value("John Doe"))
                .andExpect(jsonPath("$.data[1].loanee_name").value("Jane Smith"));
    }

    @Test
    void testAddLoanSuccess() throws Exception{
        //given
        Sample s1 = new Sample();
        s1.setSample_ID("0003");
        s1.setName("Abbott");
        s1.setMonnig_number("M398.1");
        s1.setCountry("USA");
        s1.setSample_class("Ordinary Chondrite");
        s1.setSample_group("H");
        s1.setDate_found_year("1951");
        s1.setSample_weight_g((float)325.1);

        Sample s2 = new Sample();
        s2.setSample_ID("0004");
        s2.setName("Abee");
        s2.setMonnig_number("M499.2");
        s2.setCountry("Canada");
        s2.setSample_class("Enstatite Chondrite");
        s2.setSample_group("EH");
        s2.setDate_found_year("1952");
        s2.setSample_weight_g((float)453.1);

        LoanDto loanDto = new LoanDto(null, null,
                "John Doe",
                "johndoe@example.com",
                "Meteorite Research Institute",
                "123 Meteorite Street, Cityville, USA",
                "2024-04-01 10:00:00",
                "2024-04-15 10:00:00",
                "Fragile meteorite, handle with care.", false);
        String json = this.objectMapper.writeValueAsString(loanDto);

        Loan l1 = new Loan();
        l1.setLoan_ID("0001");
        l1.setLoanee_name("John Doe");
        l1.setLoanee_email("johndoe@example.com");
        l1.setLoanee_institution("Meteorite Research Institute");
        l1.setLoanee_address("123 Meteorite Street, Cityville, USA");
        l1.setLoan_start_date("2024-04-01 10:00:00");
        l1.setLoan_due_date("2024-04-15 10:00:00");
        l1.setLoan_notes("Fragile meteorite, handle with care.");

        given(this.loanService.save(Mockito.any(Loan.class))).willReturn(l1);

        //when and then
        this.mockMvc.perform(post("/api/loan/create").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Add Success"))
                .andExpect(jsonPath("$.data.id").isNotEmpty())
                .andExpect(jsonPath("$.data.samples_on_loan").value(l1.getSamples_on_loan()))
                .andExpect(jsonPath("$.data.loanee_name").value(l1.getLoanee_name()))
                .andExpect(jsonPath("$.data.loanee_email").value(l1.getLoanee_email()))
                .andExpect(jsonPath("$.data.loanee_institution").value(l1.getLoanee_institution()))
                .andExpect(jsonPath("$.data.loanee_address").value(l1.getLoanee_address()))
                .andExpect(jsonPath("$.data.loan_start_date").value(l1.getLoan_start_date()))
                .andExpect(jsonPath("$.data.loan_due_date").value(l1.getLoan_due_date()))
                .andExpect(jsonPath("$.data.loan_notes").value(l1.getLoan_notes()));
    }

    @Test
    void testUpdateLoanSuccess() throws Exception{
        //given
        Sample s1 = new Sample();
        s1.setSample_ID("0003");
        s1.setName("Abbott");
        s1.setMonnig_number("M398.1");
        s1.setCountry("USA");
        s1.setSample_class("Ordinary Chondrite");
        s1.setSample_group("H");
        s1.setDate_found_year("1951");
        s1.setSample_weight_g((float)325.1);

        Sample s2 = new Sample();
        s2.setSample_ID("0004");
        s2.setName("Abee");
        s2.setMonnig_number("M499.2");
        s2.setCountry("Canada");
        s2.setSample_class("Enstatite Chondrite");
        s2.setSample_group("EH");
        s2.setDate_found_year("1952");
        s2.setSample_weight_g((float)453.1);

        LoanDto loanDto = new LoanDto("0001",
                null,
                "John Doe",
                "johndoe@example.com",
                "Meteorite Research Institute",
                "123 Meteorite Street, Cityville, USA",
                "2024-04-01 10:00:00",
                "2024-04-15 10:00:00",
                "Fragile meteorite, handle with care.", false);
        String json = this.objectMapper.writeValueAsString(loanDto);

        Loan l2 = new Loan();
        l2.setLoan_ID("0001");
        l2.setLoanee_name("Jane Smith");
        l2.setLoanee_email("janesmith@example.com");
        l2.setLoanee_institution("Space Exploration Society");
        l2.setLoanee_address("456 Galaxy Avenue, Star City, Canada");
        l2.setLoan_start_date("2024-03-20 09:30:00");
        l2.setLoan_due_date("2024-04-10 09:30:00");
        l2.setLoan_notes("Rare meteorite, research project.");

        given(this.loanService.update(eq("0001"), Mockito.any(Loan.class))).willReturn(l2);

        //when and then
        this.mockMvc.perform(put("/api/loan/update/0001").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Update Success"))
                .andExpect(jsonPath("$.data.id").value("0001"))
                .andExpect(jsonPath("$.data.samples_on_loan").value(l2.getSamples_on_loan()))
                .andExpect(jsonPath("$.data.loanee_name").value(l2.getLoanee_name()))
                .andExpect(jsonPath("$.data.loanee_email").value(l2.getLoanee_email()))
                .andExpect(jsonPath("$.data.loanee_institution").value(l2.getLoanee_institution()))
                .andExpect(jsonPath("$.data.loanee_address").value(l2.getLoanee_address()))
                .andExpect(jsonPath("$.data.loan_start_date").value(l2.getLoan_start_date()))
                .andExpect(jsonPath("$.data.loan_due_date").value(l2.getLoan_due_date()))
                .andExpect(jsonPath("$.data.loan_notes").value(l2.getLoan_notes()))
                .andExpect(jsonPath("$.data.isArchived").value(l2.isArchived()));
    }

    @Test
    void testUpdateLoanErrorWithNonExistsId() throws Exception {
        //given
        Sample s1 = new Sample();
        s1.setSample_ID("0003");
        s1.setName("Abbott");
        s1.setMonnig_number("M398.1");
        s1.setCountry("USA");
        s1.setSample_class("Ordinary Chondrite");
        s1.setSample_group("H");
        s1.setDate_found_year("1951");
        s1.setSample_weight_g((float)325.1);

        Sample s2 = new Sample();
        s2.setSample_ID("0004");
        s2.setName("Abee");
        s2.setMonnig_number("M499.2");
        s2.setCountry("Canada");
        s2.setSample_class("Enstatite Chondrite");
        s2.setSample_group("EH");
        s2.setDate_found_year("1952");
        s2.setSample_weight_g((float)453.1);

        LoanDto loanDto = new LoanDto("0001",
                null,
                "John Doe",
                "johndoe@example.com",
                "Meteorite Research Institute",
                "123 Meteorite Street, Cityville, USA",
                "2024-04-01 10:00:00",
                "2024-04-15 10:00:00",
                "Fragile meteorite, handle with care.", false);
        String json = this.objectMapper.writeValueAsString(loanDto);

        given(this.loanService.update(eq("0001") ,Mockito.any(Loan.class))).willThrow(new ObjectNotFoundException("loan", "0001"));

        this.mockMvc.perform(put("/api/loan/update/0001").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(StatusCode.NOT_FOUND))
                .andExpect(jsonPath("$.message").value("Could not find loan with Id 0001"))
                .andExpect(jsonPath("$.data").isEmpty());
    }
    @Test
    void testArchiveLoanSuccess() throws Exception {
        // Given
        LoanDto loanDto = new LoanDto("0001",
                null,
                "John Doe",
                "johndoe@example.com",
                "Meteorite Research Institute",
                "123 Meteorite Street, Cityville, USA",
                "2024-04-01 10:00:00",
                "2024-04-15 10:00:00",
                "Fragile meteorite, handle with care.", false);
        String json = this.objectMapper.writeValueAsString(loanDto);

        Loan l2 = new Loan();
        l2.setLoan_ID("0001");
        l2.setLoanee_name("John Doe");
        l2.setLoanee_email("johndoe@example.com");
        l2.setLoanee_institution("Meteorite Research Institute");
        l2.setLoanee_address("123 Meteorite Street, Cityville, USA");
        l2.setLoan_start_date("2024-04-01 10:00:00");
        l2.setLoan_due_date("2024-04-15 10:00:00");
        l2.setLoan_notes("Fragile meteorite, handle with care.");
        l2.setArchived(true);

        given(this.loanService.update(eq("0001"), Mockito.any(Loan.class))).willReturn(l2);

        //when and then
        this.mockMvc.perform(put("/api/loan/update/0001").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Update Success"))
                .andExpect(jsonPath("$.data.id").value("0001"))
                .andExpect(jsonPath("$.data.samples_on_loan").value(l2.getSamples_on_loan()))
                .andExpect(jsonPath("$.data.loanee_name").value(l2.getLoanee_name()))
                .andExpect(jsonPath("$.data.loanee_email").value(l2.getLoanee_email()))
                .andExpect(jsonPath("$.data.loanee_institution").value(l2.getLoanee_institution()))
                .andExpect(jsonPath("$.data.loanee_address").value(l2.getLoanee_address()))
                .andExpect(jsonPath("$.data.loan_start_date").value(l2.getLoan_start_date()))
                .andExpect(jsonPath("$.data.loan_due_date").value(l2.getLoan_due_date()))
                .andExpect(jsonPath("$.data.loan_notes").value(l2.getLoan_notes()))
                .andExpect(jsonPath("$.data.isArchived").value(true));


    }
    @Test
    void testUnArchiveLoanSuccess() throws Exception {
        // Given
        LoanDto loanDto = new LoanDto("0001",
                null,
                "John Doe",
                "johndoe@example.com",
                "Meteorite Research Institute",
                "123 Meteorite Street, Cityville, USA",
                "2024-04-01 10:00:00",
                "2024-04-15 10:00:00",
                "Fragile meteorite, handle with care.", false);
        String json = this.objectMapper.writeValueAsString(loanDto);

        Loan l2 = new Loan();
        l2.setLoan_ID("0001");
        l2.setLoanee_name("John Doe");
        l2.setLoanee_email("johndoe@example.com");
        l2.setLoanee_institution("Meteorite Research Institute");
        l2.setLoanee_address("123 Meteorite Street, Cityville, USA");
        l2.setLoan_start_date("2024-04-01 10:00:00");
        l2.setLoan_due_date("2024-04-15 10:00:00");
        l2.setLoan_notes("Fragile meteorite, handle with care.");
        l2.setArchived(false);

        given(this.loanService.update(eq("0001"), Mockito.any(Loan.class))).willReturn(l2);

        //when and then
        this.mockMvc.perform(put("/api/loan/update/0001").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Update Success"))
                .andExpect(jsonPath("$.data.id").value("0001"))
                .andExpect(jsonPath("$.data.samples_on_loan").value(l2.getSamples_on_loan()))
                .andExpect(jsonPath("$.data.loanee_name").value(l2.getLoanee_name()))
                .andExpect(jsonPath("$.data.loanee_email").value(l2.getLoanee_email()))
                .andExpect(jsonPath("$.data.loanee_institution").value(l2.getLoanee_institution()))
                .andExpect(jsonPath("$.data.loanee_address").value(l2.getLoanee_address()))
                .andExpect(jsonPath("$.data.loan_start_date").value(l2.getLoan_start_date()))
                .andExpect(jsonPath("$.data.loan_due_date").value(l2.getLoan_due_date()))
                .andExpect(jsonPath("$.data.loan_notes").value(l2.getLoan_notes()))
                .andExpect(jsonPath("$.data.isArchived").value(false));


    }
}
