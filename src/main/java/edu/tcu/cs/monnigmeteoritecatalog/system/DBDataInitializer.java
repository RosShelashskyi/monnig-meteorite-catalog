package edu.tcu.cs.monnigmeteoritecatalog.system;

import edu.tcu.cs.monnigmeteoritecatalog.sample.Sample;
import edu.tcu.cs.monnigmeteoritecatalog.sample.SampleRepository;
import edu.tcu.cs.monnigmeteoritecatalog.utils.IdWorker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBDataInitializer implements CommandLineRunner {

    private final SampleRepository sampleRepository;

    private final IdWorker idWorker;

    public DBDataInitializer(SampleRepository sampleRepository, IdWorker idWorker) {
        this.sampleRepository = sampleRepository;
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

        sampleRepository.save(s1);
        sampleRepository.save(s2);
    }
}
