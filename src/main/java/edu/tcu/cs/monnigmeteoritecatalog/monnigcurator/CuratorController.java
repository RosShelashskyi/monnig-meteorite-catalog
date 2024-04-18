package edu.tcu.cs.monnigmeteoritecatalog.monnigcurator;

import edu.tcu.cs.monnigmeteoritecatalog.monnigcurator.converter.CuratorToCuratorDtoConverter;
import edu.tcu.cs.monnigmeteoritecatalog.monnigcurator.dto.CuratorDto;
import edu.tcu.cs.monnigmeteoritecatalog.system.Result;
import edu.tcu.cs.monnigmeteoritecatalog.system.StatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class CuratorController {
    private final CuratorService curatorService;

    private final CuratorToCuratorDtoConverter curatorToCuratorDtoConverter;

    public CuratorController(CuratorService curatorService, CuratorToCuratorDtoConverter curatorToCuratorDtoConverter) {
        this.curatorService = curatorService;
        this.curatorToCuratorDtoConverter = curatorToCuratorDtoConverter;
    }
    @GetMapping
    public Result findAllUsers() {
        List<Curator> foundCurators = this.curatorService.findAll();

        List<CuratorDto> curatorDtos = foundCurators.stream().map(this.curatorToCuratorDtoConverter::convert).toList();

        return new Result(true, StatusCode.SUCCESS, "Find All Success", curatorDtos);

    }
}
