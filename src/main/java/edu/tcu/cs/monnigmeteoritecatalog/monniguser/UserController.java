package edu.tcu.cs.monnigmeteoritecatalog.monniguser;

import edu.tcu.cs.monnigmeteoritecatalog.monniguser.converter.UserDtoToUserConverter;
import edu.tcu.cs.monnigmeteoritecatalog.monniguser.converter.UserToUserDtoConverter;
import edu.tcu.cs.monnigmeteoritecatalog.monniguser.dto.UserDto;
import edu.tcu.cs.monnigmeteoritecatalog.system.Result;
import edu.tcu.cs.monnigmeteoritecatalog.system.StatusCode;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.endpoint.base-url}/users")
public class UserController {
    private final UserService userService;

    private final UserToUserDtoConverter curatorToCuratorDtoConverter;

    private final UserDtoToUserConverter curatorDtoToCuratorConverter;

    public UserController(UserService userService, UserToUserDtoConverter curatorToCuratorDtoConverter, UserDtoToUserConverter curatorDtoToCuratorConverter) {
        this.userService = userService;
        this.curatorToCuratorDtoConverter = curatorToCuratorDtoConverter;
        this.curatorDtoToCuratorConverter = curatorDtoToCuratorConverter;
    }
    @GetMapping("/all")
    public Result findAllUsers() {
        List<MonnigUser> foundMonnigUsers = this.userService.findAll();

        List<UserDto> userDtos = foundMonnigUsers.stream().map(this.curatorToCuratorDtoConverter::convert).toList();

        return new Result(true, StatusCode.SUCCESS, "Find All Success", userDtos);
    }
    @PostMapping("/add")
    public Result addUser(@Valid @RequestBody MonnigUser monnigUser) {
        MonnigUser savedUser = userService.save(monnigUser);
        UserDto savedUserDto = this.curatorToCuratorDtoConverter.convert(savedUser);
        return new Result(true, StatusCode.SUCCESS, "Add Success", savedUserDto);
    }
}
