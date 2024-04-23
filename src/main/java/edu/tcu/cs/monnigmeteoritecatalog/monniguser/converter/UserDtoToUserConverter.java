package edu.tcu.cs.monnigmeteoritecatalog.monniguser.converter;

import edu.tcu.cs.monnigmeteoritecatalog.monniguser.MonnigUser;
import edu.tcu.cs.monnigmeteoritecatalog.monniguser.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import org.springframework.stereotype.Component;

@Component
public class UserDtoToUserConverter implements Converter<UserDto, MonnigUser> {

    @Override
    public MonnigUser convert(UserDto source) {
        MonnigUser monnigUser = new MonnigUser();
        monnigUser.setUsername(source.username());
        monnigUser.setEnabled(source.enabled());
        monnigUser.setRoles(source.roles());
        return monnigUser;
    }
}
