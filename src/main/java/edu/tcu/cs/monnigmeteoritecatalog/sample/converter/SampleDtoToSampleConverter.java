package edu.tcu.cs.monnigmeteoritecatalog.sample.converter;

import edu.tcu.cs.monnigmeteoritecatalog.sample.Sample;
import edu.tcu.cs.monnigmeteoritecatalog.sample.dto.SampleDto;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

@Component
public class SampleDtoToSampleConverter implements Converter<SampleDto, Sample> {

    @Override
    public Sample convert(SampleDto source){
        Sample sample = new Sample();
        sample.setSample_ID(source.id());
        sample.setName(source.name());
        sample.setMonnig_number(source.monnig_number());
        sample.setCountry(source.country());
        sample.setSample_class(source.sample_class());
        sample.setSample_group(source.group());
        sample.setDate_found_year(source.date_found_year());
        sample.setSample_weight_g(source.sample_weight_g());

        return sample;
    }
}
