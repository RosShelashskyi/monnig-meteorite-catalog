package edu.tcu.cs.monnigmeteoritecatalog.security;

import edu.tcu.cs.monnigmeteoritecatalog.monniguser.MonnigUser;
import edu.tcu.cs.monnigmeteoritecatalog.monniguser.MyUserPrincipal;
import edu.tcu.cs.monnigmeteoritecatalog.monniguser.converter.UserToUserDtoConverter;
import edu.tcu.cs.monnigmeteoritecatalog.monniguser.dto.UserDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final JwtProvider jwtProvider;

    private final UserToUserDtoConverter userToUserDtoConverter;

    public AuthService(JwtProvider jwtProvider, UserToUserDtoConverter usertouserdtoconverter, UserToUserDtoConverter userToUserDtoConverter) {
        this.jwtProvider = jwtProvider;
        this.userToUserDtoConverter = userToUserDtoConverter;
    }

    public Map<String, Object> createLoginInfo(Authentication authentication) {
        // Create user info.
        MyUserPrincipal principal = (MyUserPrincipal)authentication.getPrincipal();
        MonnigUser monnigUser = principal.getMonnigUser();
        UserDto userDto = this.userToUserDtoConverter.convert(monnigUser);
        // Create a JWT
        String token = this.jwtProvider.createToken(authentication);
        Map<String, Object> loginResultMap = new HashMap<>();
        loginResultMap.put("userInfo", userDto);
        loginResultMap.put("token", token);
        return loginResultMap;
    }
}
