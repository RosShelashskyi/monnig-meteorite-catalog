package edu.tcu.cs.monnigmeteoritecatalog.sample;

import edu.tcu.cs.monnigmeteoritecatalog.system.exception.ObjectNotFoundException;
import edu.tcu.cs.monnigmeteoritecatalog.utils.IdWorker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

//class for testing SampleRepository
@ExtendWith(MockitoExtension.class)
@ActiveProfiles(value = "dev")
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

        this.samples = new ArrayList<>();
        this.samples.add(s1);
        this.samples.add(s2);
    }

    @AfterEach
    void tearDown(){
    }

    @Test
    void testFindByIdSuccess(){
        //given
        Sample s = new Sample();
        s.setSample_ID(idWorker.nextId() + "");
        s.setName("Abbott");
        s.setMonnig_number("M398.1");
        s.setCountry("USA");
        s.setSample_class("Ordinary Chondrite");
        s.setSample_group("H");
        s.setDate_found_year("1951");
        s.setSample_weight_g((float)325.1);

        given(sampleRepository.findById(s.getSample_ID())).willReturn(Optional.of(s));

        //when
        Sample returnedSample = sampleService.findById(s.getSample_ID());

        //then
        assertThat(returnedSample.getSample_ID()).isEqualTo(s.getSample_ID());
        assertThat(returnedSample.getName()).isEqualTo(s.getName());
        assertThat(returnedSample.getMonnig_number()).isEqualTo(s.getMonnig_number());
        assertThat(returnedSample.getCountry()).isEqualTo(s.getCountry());
        assertThat(returnedSample.getSample_class()).isEqualTo(s.getSample_class());
        assertThat(returnedSample.getSample_group()).isEqualTo(s.getSample_group());
        assertThat(returnedSample.getDate_found_year()).isEqualTo(s.getDate_found_year());
        assertThat(returnedSample.getSample_weight_g()).isEqualTo(s.getSample_weight_g());
        verify(this.sampleRepository, times(1)).findById(s.getSample_ID());
    }

    @Test
    void testFindByIdNotFound(){
        //given
        given(sampleRepository.findById(Mockito.any(String.class))).willReturn(Optional.empty());

        //when
        Throwable thrown = catchThrowable(()->{
            Sample returnedSample = sampleService.findById("1");
        });

        //then
        assertThat(thrown)
                .isInstanceOf(ObjectNotFoundException.class)
                .hasMessage("Could not find sample with Id 1");
        verify(sampleRepository, times(1)).findById("1");
    }

    @Test
    void testFindAllSuccess(){
        //given
        given(sampleRepository.findAll()).willReturn(this.samples);

        //when
        List<Sample> actualSamples = sampleService.findAll();

        //then
        assertThat(actualSamples.size()).isEqualTo(this.samples.size());
        verify(sampleRepository, times(1)).findAll();
    }

    @Test
    void testSaveSuccess(){
        //given
        Sample newSample = new Sample();
        newSample.setName("Abernathy");
        newSample.setMonnig_number("M239.1");
        newSample.setCountry("USA");
        newSample.setSample_class("Ordinary Chondrite");
        newSample.setSample_group("L");
        newSample.setDate_found_year("1941");
        newSample.setSample_weight_g((float)453.1);

        given(idWorker.nextId()).willReturn(123456L);
        given(sampleRepository.save(newSample)).willReturn(newSample);

        //when
        Sample savedSample = sampleService.save(newSample);

        //then
        assertThat(savedSample.getSample_ID()).isEqualTo("123456");
        assertThat(savedSample.getName()).isEqualTo(newSample.getName());
        assertThat(savedSample.getMonnig_number()).isEqualTo(newSample.getMonnig_number());
        assertThat(savedSample.getCountry()).isEqualTo(newSample.getCountry());
        assertThat(savedSample.getSample_class()).isEqualTo(newSample.getSample_class());
        assertThat(savedSample.getSample_group()).isEqualTo(newSample.getSample_group());
        assertThat(savedSample.getDate_found_year()).isEqualTo(newSample.getDate_found_year());
        assertThat(savedSample.getSample_weight_g()).isEqualTo(newSample.getSample_weight_g());
        verify(sampleRepository, times(1)).save(newSample);
    }

    @Test
    void testUpdateSuccess(){
        //given
        Sample oldSample = new Sample();
        oldSample.setSample_ID("1");
        oldSample.setName("Abernathy");
        oldSample.setMonnig_number("M239.1");
        oldSample.setCountry("USA");
        oldSample.setSample_class("Ordinary Chondrite");
        oldSample.setSample_group("L");
        oldSample.setDate_found_year("1941");
        oldSample.setSample_weight_g((float)453.1);

        Sample update = new Sample();
        update.setName("Abbott");
        update.setMonnig_number("M398.1");
        update.setCountry("USA");
        update.setSample_class("Ordinary Chondrite");
        update.setSample_group("H");
        update.setDate_found_year("1951");
        update.setSample_weight_g((float)325.1);

        given(sampleRepository.findById("1")).willReturn(Optional.of(oldSample));
        given(sampleRepository.save(oldSample)).willReturn(oldSample);

        //when
        Sample updatedSample = sampleService.update("1", update);

        //then
        assertThat(updatedSample.getSample_ID()).isEqualTo("1");
        assertThat(updatedSample.getName()).isEqualTo(update.getName());
        verify(sampleRepository, times(1)).findById("1");
        verify(sampleRepository, times(1)).save(oldSample);
    }

    @Test
    void testUpdateNotFound(){
        //given
        Sample update = new Sample();
        update.setName("Abbott");
        update.setMonnig_number("M398.1");
        update.setCountry("USA");
        update.setSample_class("Ordinary Chondrite");
        update.setSample_group("H");
        update.setDate_found_year("1951");
        update.setSample_weight_g((float)325.1);

        given(sampleRepository.findById("1")).willReturn(Optional.empty());

        //when
        assertThrows(ObjectNotFoundException.class, ()->{
           sampleService.update("1", update);
        });

        //then
        verify(sampleRepository, times(1)).findById("1");
    }

    @Test
    void testDeleteSuccess(){
        //given
        Sample s = new Sample();
        s.setSample_ID("1");
        s.setName("Abbott");
        s.setMonnig_number("M398.1");
        s.setCountry("USA");
        s.setSample_class("Ordinary Chondrite");
        s.setSample_group("H");
        s.setDate_found_year("1951");
        s.setSample_weight_g((float)325.1);

        given(sampleRepository.findById("1")).willReturn(Optional.of(s));
        doNothing().when(sampleRepository).deleteById("1");

        //when
        sampleService.delete("1");

        //then
        verify(sampleRepository, times(1)).deleteById("1");
    }

    @Test
    void testDeleteNotFound(){
        //given
        given(sampleRepository.findById("1")).willReturn(Optional.empty());

        //when
        assertThrows(ObjectNotFoundException.class, ()->{
            sampleService.delete("1");
        });

        //then
        verify(sampleRepository, times(1)).findById(("1"));
    }
}
