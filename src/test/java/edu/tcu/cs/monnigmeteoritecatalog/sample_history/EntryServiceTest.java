package edu.tcu.cs.monnigmeteoritecatalog.sample_history;

import edu.tcu.cs.monnigmeteoritecatalog.sample.Sample;
import edu.tcu.cs.monnigmeteoritecatalog.samplehistory.Entry;
import edu.tcu.cs.monnigmeteoritecatalog.samplehistory.EntryRepository;
import edu.tcu.cs.monnigmeteoritecatalog.samplehistory.EntryService;
import edu.tcu.cs.monnigmeteoritecatalog.utils.IdWorker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EntryServiceTest {

    @Mock
    EntryRepository entryRepository;

    @Mock
    IdWorker idWorker;

    @InjectMocks
    EntryService entryService;


    // Maybe we need to change and just call it "entries" or something
    List<Entry> entryList;

    @BeforeEach
    void setUp() {
        Entry entry1 = new Entry();
        entry1.setEntry_id(idWorker.nextId() + "");
        entry1.setDate("11/1/2023");
        entry1.setCategory("Created");
        entry1.setNotes("Migrated from Old Monnig Database");

        Entry entry2 = new Entry();
        entry2.setEntry_id(idWorker.nextId() + "");
        entry2.setDate("12/5/2023");
        entry2.setCategory("Cut");
        entry2.setNotes("Cut into three subsamples");

        this.entryList = new ArrayList<>();
        this.entryList.add(entry1);
        this.entryList.add(entry2);
    }
    @AfterEach
    void tearDown() {

    }
    // Find a specific history entry by its unique ID
    @Test
    void testFindByIdSuccess() {

        // Create a fake sample to link to
        Sample s = new Sample();
        s.setSample_ID(idWorker.nextId() + "");
        s.setName("Abbott");
        s.setMonnig_number("M398.1");
        s.setCountry("USA");
        s.setSample_class("Ordinary Chondrite");
        s.setSample_group("H");
        s.setDate_found_year("1951");
        s.setSample_weight_g((float)325.1);

        // Fake entry
        Entry entry = new Entry();
        entry.setEntry_id(idWorker.nextId() + "");
        entry.setDate("11/1/2023");
        entry.setCategory("Created");
        entry.setNotes("Migrated from Old Monnig Database");
        entry.setOwner_id(s.getSample_ID());

        // Given
        given(this.entryRepository.findById(entry.getEntry_id())).willReturn(Optional.of(entry));

        // Entry
        Entry returnedEntry = entryService.findById(entry.getEntry_id());

        // Then
        assertThat(returnedEntry.getEntry_id()).isEqualTo(entry.getEntry_id());
        assertThat(returnedEntry.getDate()).isEqualTo(entry.getDate());
        assertThat(returnedEntry.getCategory()).isEqualTo(entry.getCategory());
        assertThat(returnedEntry.getNotes()).isEqualTo(entry.getNotes());
        verify(entryRepository, times(1)).findById(entry.getEntry_id());

    }

    @Test
    void testFindAllSuccess(){
        //given
        given(entryRepository.findAll()).willReturn(this.entryList);

        //when
        List<Entry> actualEntries = entryService.findAll();

        //then
        assertThat(actualEntries.size()).isEqualTo(this.entryList.size());
        verify(entryRepository, times(1)).findAll();
    }
}
