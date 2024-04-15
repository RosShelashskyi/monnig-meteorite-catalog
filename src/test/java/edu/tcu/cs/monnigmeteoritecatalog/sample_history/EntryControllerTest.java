package edu.tcu.cs.monnigmeteoritecatalog.sample_history;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tcu.cs.monnigmeteoritecatalog.samplehistory.Entry;
import edu.tcu.cs.monnigmeteoritecatalog.samplehistory.EntryService;
import edu.tcu.cs.monnigmeteoritecatalog.utils.IdWorker;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
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
        Entry entry1 = new Entry();
        entry1.setEntry_id(idWorker.nextId() + "");
        entry1.setDate("11/1/2023");
        entry1.setCategory("Created");
        entry1.setNotes("Migrated from Old Monnig Database");

        Entry entry2 = new Entry();
        entry1.setEntry_id(idWorker.nextId() + "");
        entry1.setDate("12/5/2023");
        entry1.setCategory("Cut");
        entry1.setNotes("Cut into three subsamples");

        this.entryList = new ArrayList<>();
        this.entryList.add(entry1);
        this.entryList.add(entry2);
    }
}
