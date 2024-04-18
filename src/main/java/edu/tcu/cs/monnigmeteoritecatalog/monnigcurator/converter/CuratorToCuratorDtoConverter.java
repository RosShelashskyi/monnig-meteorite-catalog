package edu.tcu.cs.monnigmeteoritecatalog.monnigcurator.converter;

import edu.tcu.cs.monnigmeteoritecatalog.monnigcurator.Curator;
import edu.tcu.cs.monnigmeteoritecatalog.monnigcurator.dto.CuratorDto;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

@Component
public class CuratorToCuratorDtoConverter implements Converter<Curator, CuratorDto> {
    @Override
    public CuratorDto convert(Curator source) {
        return new CuratorDto(source.getId(), source.getUsername(), source.isEnabled(), source.getPassword(), source.getRoles());
    }
}
