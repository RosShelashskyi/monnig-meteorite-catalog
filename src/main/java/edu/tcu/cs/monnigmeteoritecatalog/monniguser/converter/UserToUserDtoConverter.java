package edu.tcu.cs.monnigmeteoritecatalog.monniguser.converter;

import edu.tcu.cs.monnigmeteoritecatalog.monniguser.MonnigUser;
import edu.tcu.cs.monnigmeteoritecatalog.monniguser.dto.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

@Component
public class UserToUserDtoConverter implements Converter<MonnigUser, UserDto> {
    @Override
    public UserDto convert(MonnigUser source) {
        return new UserDto(source.getId(), source.getUsername(), source.isEnabled(), source.getPassword(), source.getRoles());
    }
}
