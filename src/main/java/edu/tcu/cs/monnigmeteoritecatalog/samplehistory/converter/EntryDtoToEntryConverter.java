package edu.tcu.cs.monnigmeteoritecatalog.samplehistory.converter;

import edu.tcu.cs.monnigmeteoritecatalog.samplehistory.Entry;
import edu.tcu.cs.monnigmeteoritecatalog.samplehistory.dto.EntryDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EntryDtoToEntryConverter implements Converter<EntryDto, Entry> {
    @Override
    public Entry convert(EntryDto source) {
        Entry entry = new Entry();
        entry.setEntry_id(source.id());
        entry.setDate(source.date());
        entry.setCategory(source.category());
        entry.setNotes(source.notes());
        return entry;
    }
}
