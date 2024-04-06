package edu.tcu.cs.monnigmeteoritecatalog.sample;

import edu.tcu.cs.monnigmeteoritecatalog.sample.converter.SampleDtoToSampleConverter;
import edu.tcu.cs.monnigmeteoritecatalog.sample.converter.SampleToSampleDtoConverter;
import edu.tcu.cs.monnigmeteoritecatalog.sample.dto.SampleDto;
import edu.tcu.cs.monnigmeteoritecatalog.system.Result;
import edu.tcu.cs.monnigmeteoritecatalog.system.StatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/samples")
public class SampleController {
    private final SampleService sampleService;

    private final SampleToSampleDtoConverter sampleToSampleDtoConverter;

    private final SampleDtoToSampleConverter sampleDtoToSampleConverter;

    public SampleController(SampleService sampleService, SampleToSampleDtoConverter sampleToSampleDtoConverter, SampleDtoToSampleConverter sampleDtoToSampleConverter) {
        this.sampleService = sampleService;
        this.sampleToSampleDtoConverter = sampleToSampleDtoConverter;
        this.sampleDtoToSampleConverter = sampleDtoToSampleConverter;
    }
    @GetMapping("/{sampleId}")
    public Result findSampleById(@PathVariable String sampleId) {
        Sample foundSample = this.sampleService.findById(sampleId);
        SampleDto sampleDto = this.sampleToSampleDtoConverter.convert(foundSample);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", sampleDto);
    }

}
