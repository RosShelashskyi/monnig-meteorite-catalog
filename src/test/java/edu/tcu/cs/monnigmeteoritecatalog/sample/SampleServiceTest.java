package edu.tcu.cs.monnigmeteoritecatalog.sample;

import edu.tcu.cs.monnigmeteoritecatalog.utils.IdWorker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

//class for testing SampleRepository
@ExtendWith(MockitoExtension.class)
class SampleServiceTest {

    //set up mock objects
    @Mock
    SampleRepository sampleRepository;

    @Mock
    IdWorker idWorker;

    @InjectMocks
    SampleService sampleService;

    List<Sample> samples;

    @BeforeEach
    void setUp(){
        Sample s1 = new Sample();
        s1.setSample_ID(idWorker.nextId() + "");
        s1.setName("Abbott");
        s1.setMonnig_number("M398.1");
        s1.setCountry("USA");
        s1.setSample_class("Ordinary Chondrite");
        s1.setGroup("H");
        s1.setDate_found_year("1951");
        s1.setSample_weight_g((float)325.1);

        Sample s2 = new Sample();
        s2.setSample_ID(idWorker.nextId() + "");
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
    void tearDown(){
    }
}
