package edu.tcu.cs.monnigmeteoritecatalog.monniguser;

import edu.tcu.cs.monnigmeteoritecatalog.monniguser.converter.UserToUserDtoConverter;
import edu.tcu.cs.monnigmeteoritecatalog.monniguser.dto.UserDto;
import edu.tcu.cs.monnigmeteoritecatalog.system.Result;
import edu.tcu.cs.monnigmeteoritecatalog.system.StatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.endpoint.base-url}/users")
public class UserController {
    private final UserService userService;

    private final UserToUserDtoConverter curatorToCuratorDtoConverter;

    public UserController(UserService userService, UserToUserDtoConverter curatorToCuratorDtoConverter) {
        this.userService = userService;
        this.curatorToCuratorDtoConverter = curatorToCuratorDtoConverter;
    }
    @GetMapping
    public Result findAllUsers() {
        List<MonnigUser> foundMonnigUsers = this.userService.findAll();

        List<UserDto> userDtos = foundMonnigUsers.stream().map(this.curatorToCuratorDtoConverter::convert).toList();

        return new Result(true, StatusCode.SUCCESS, "Find All Success", userDtos);

    }
}
