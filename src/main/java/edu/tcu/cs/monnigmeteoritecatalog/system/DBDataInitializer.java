package edu.tcu.cs.monnigmeteoritecatalog.system;

import edu.tcu.cs.monnigmeteoritecatalog.sample.Sample;
import edu.tcu.cs.monnigmeteoritecatalog.sample.SampleRepository;
import edu.tcu.cs.monnigmeteoritecatalog.samplehistory.Entry;
import edu.tcu.cs.monnigmeteoritecatalog.samplehistory.EntryRepository;
import edu.tcu.cs.monnigmeteoritecatalog.utils.IdWorker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DBDataInitializer implements CommandLineRunner {

    private final SampleRepository sampleRepository;

    private final EntryRepository entryRepository;

    private final IdWorker idWorker;

    public DBDataInitializer(SampleRepository sampleRepository, EntryRepository entryRepository, IdWorker idWorker) {
        this.sampleRepository = sampleRepository;
        this.entryRepository = entryRepository;
        this.idWorker = idWorker;
    }

    @Override
    public void run(String... args) throws Exception {
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

        // Entries
        Entry entry1 = new Entry();
        entry1.setEntry_id(idWorker.nextId() + "");
        entry1.setDate("11/1/2023");
        entry1.setCategory("Created");
        entry1.setNotes("Migrated from Old Monnig Database");
        entry1.setOwner_id(s1.getSample_ID());


        Entry entry2 = new Entry();
        entry2.setEntry_id(idWorker.nextId() + "");
        entry2.setDate("12/5/2023");
        entry2.setCategory("Cut");
        entry2.setNotes("Cut into three subsamples");
        entry2.setOwner_id(s2.getSample_ID());

        entryRepository.save(entry1);
        entryRepository.save(entry2);

        sampleRepository.save(s1);
        sampleRepository.save(s2);

    }
}
