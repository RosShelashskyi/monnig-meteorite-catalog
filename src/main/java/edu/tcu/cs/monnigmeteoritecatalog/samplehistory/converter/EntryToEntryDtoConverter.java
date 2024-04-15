package edu.tcu.cs.monnigmeteoritecatalog.samplehistory.converter;

import edu.tcu.cs.monnigmeteoritecatalog.sample.converter.SampleToSampleDtoConverter;
import edu.tcu.cs.monnigmeteoritecatalog.samplehistory.Entry;
import edu.tcu.cs.monnigmeteoritecatalog.samplehistory.dto.EntryDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EntryToEntryDtoConverter implements Converter<Entry, EntryDto> {

    private final SampleToSampleDtoConverter sampleToSampleDtoConverter;

    public EntryToEntryDtoConverter(SampleToSampleDtoConverter sampleToSampleDtoConverter) {
        this.sampleToSampleDtoConverter = sampleToSampleDtoConverter;
    }

    @Override
    public EntryDto convert(Entry source) {

        return new EntryDto(source.getEntry_id(),
                            source.getDate(),
                            source.getCategory(),
                            source.getNotes(),
                            source.getOwner_id());
    }
}
