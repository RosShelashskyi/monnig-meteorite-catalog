package edu.tcu.cs.monnigmeteoritecatalog.sample_history;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tcu.cs.monnigmeteoritecatalog.sample.Sample;
import edu.tcu.cs.monnigmeteoritecatalog.samplehistory.Entry;
import edu.tcu.cs.monnigmeteoritecatalog.samplehistory.EntryService;
import edu.tcu.cs.monnigmeteoritecatalog.samplehistory.dto.EntryDto;
import edu.tcu.cs.monnigmeteoritecatalog.system.StatusCode;
import edu.tcu.cs.monnigmeteoritecatalog.system.exception.ObjectNotFoundException;
import edu.tcu.cs.monnigmeteoritecatalog.utils.IdWorker;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles(value = "dev")
public class EntryControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Mock
    IdWorker idWorker;

    @MockBean
    EntryService entryService;

    @Autowired
    ObjectMapper objectMapper;

    List<Entry> entryList;

    @BeforeEach
    void setUp() {
        Sample s1 = new Sample();
        s1.setSample_ID("0001");
        s1.setName("Abbott");
        s1.setMonnig_number("M398.1");
        s1.setCountry("USA");
        s1.setSample_class("Ordinary Chondrite");
        s1.setSample_group("H");
        s1.setDate_found_year("1951");
        s1.setSample_weight_g((float)325.1);


        Entry entry1 = new Entry();
        entry1.setEntry_id("0002");
        entry1.setDate("11/1/2023");
        entry1.setCategory("Created");
        entry1.setNotes("Migrated from Old Monnig Database");
        entry1.setSample(s1);


        Entry entry2 = new Entry();
        entry2.setEntry_id("0003");
        entry2.setDate("12/5/2023");
        entry2.setCategory("Cut");
        entry2.setNotes("Cut into three subsamples");
        entry2.setSample(s1);

        this.entryList = new ArrayList<>();
        this.entryList.add(entry1);
        this.entryList.add(entry2);
    }
    @Test
    void testAddEntrySuccess() throws Exception {
        EntryDto entryDto = new EntryDto(null, "12/22/2023", "Cut", "Removing from database", null);
        String json = this.objectMapper.writeValueAsString(entryDto);
        // entry to test entry add
        Entry entry = new Entry();
        entry.setEntry_id(idWorker.nextId() + "");
        entry.setDate("12/22/2023");
        entry.setCategory("Cut");
        entry.setNotes("Removing from database");
        given(this.entryService.save(Mockito.any(Entry.class))).willReturn(entry);

        // When and then
        this.mockMvc.perform(post("/api/history").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Add Success"))
                .andExpect(jsonPath("$.data.id").isNotEmpty())
                .andExpect(jsonPath("$.data.date").value(entry.getDate()))
                .andExpect(jsonPath("$.data.category").value(entry.getCategory()))
                .andExpect(jsonPath("$.data.notes").value(entry.getNotes()));

    }
    @Test
    void testDeleteEntrySuccess() throws Exception {
       doNothing().when(this.entryService).delete("0002");
        this.mockMvc.perform(delete("/api/history/delete/0002").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Delete Success"))
                .andExpect(jsonPath("$.data").isEmpty());
    }
    @Test
    void testDeleteEntryErrorWithNonExistentId() throws Exception {
        doThrow(new ObjectNotFoundException("entry","0002")).when(this.entryService).delete("0002");
        this.mockMvc.perform(delete("/api/history/delete/0002").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(StatusCode.NOT_FOUND))
                .andExpect(jsonPath("$.message").value("Could not find entry with Id 0002"))
                .andExpect(jsonPath("$.data").isEmpty());
    }

}
