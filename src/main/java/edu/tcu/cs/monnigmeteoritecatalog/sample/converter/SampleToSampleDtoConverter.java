package edu.tcu.cs.monnigmeteoritecatalog.sample.converter;

import edu.tcu.cs.monnigmeteoritecatalog.sample.Sample;
import edu.tcu.cs.monnigmeteoritecatalog.sample.dto.SampleDto;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

@Component
public class SampleToSampleDtoConverter implements Converter<Sample, SampleDto> {
    @Override
    public SampleDto convert(Sample source) {
         return new SampleDto(source.getSample_ID(),
                                            source.getName(),
                                            source.getMonnig_number(),
                                            source.getCountry(),
                                            source.getSample_class(),
                                            source.getSample_group(),
                                            source.getDate_found_year(),
                                            source.getSample_weight_g());
    }

}
