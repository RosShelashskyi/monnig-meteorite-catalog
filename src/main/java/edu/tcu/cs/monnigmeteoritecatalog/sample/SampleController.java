package edu.tcu.cs.monnigmeteoritecatalog.sample;

import edu.tcu.cs.monnigmeteoritecatalog.sample.converter.SampleDtoToSampleConverter;
import edu.tcu.cs.monnigmeteoritecatalog.sample.converter.SampleToSampleDtoConverter;
import edu.tcu.cs.monnigmeteoritecatalog.sample.dto.SampleDto;
import edu.tcu.cs.monnigmeteoritecatalog.samplehistory.Entry;
import edu.tcu.cs.monnigmeteoritecatalog.samplehistory.converter.EntryToEntryDtoConverter;
import edu.tcu.cs.monnigmeteoritecatalog.samplehistory.dto.EntryDto;
import edu.tcu.cs.monnigmeteoritecatalog.system.Result;
import edu.tcu.cs.monnigmeteoritecatalog.system.StatusCode;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/samples")
public class SampleController {
    private final SampleService sampleService;

    private final SampleToSampleDtoConverter sampleToSampleDtoConverter;

    private final SampleDtoToSampleConverter sampleDtoToSampleConverter;

    private final EntryToEntryDtoConverter entryToEntryDtoConverter;

    public SampleController(SampleService sampleService, SampleToSampleDtoConverter sampleToSampleDtoConverter, SampleDtoToSampleConverter sampleDtoToSampleConverter, EntryToEntryDtoConverter entryToEntryDtoConverter) {
        this.sampleService = sampleService;
        this.sampleToSampleDtoConverter = sampleToSampleDtoConverter;
        this.sampleDtoToSampleConverter = sampleDtoToSampleConverter;
        this.entryToEntryDtoConverter = entryToEntryDtoConverter;
    }
    @GetMapping("/{sampleId}")
    public Result findSampleById(@PathVariable String sampleId) {
        Sample foundSample = this.sampleService.findById(sampleId);
        SampleDto sampleDto = this.sampleToSampleDtoConverter.convert(foundSample);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", sampleDto);
    }
    @GetMapping
    public Result findAllSamples() {
        List<Sample> foundSamples = this.sampleService.findAll();

        List<SampleDto> sampleDtos = foundSamples.stream()
                .map(this.sampleToSampleDtoConverter::convert)
                .toList();
        return new Result(true, StatusCode.SUCCESS, "Find All Success", sampleDtos);
    }
    @PostMapping
    public Result addSample(@Valid @RequestBody SampleDto sampleDto){
        Sample newSample = this.sampleDtoToSampleConverter.convert(sampleDto);
        Sample savedSample = this.sampleService.save(newSample);
        SampleDto savedSampleDto = this.sampleToSampleDtoConverter.convert(savedSample);
        return new Result(true, StatusCode.SUCCESS, "Add Success", savedSampleDto);

    }

    @PutMapping("/{sampleId}")
    public Result updateSample(@PathVariable String sampleId, @Valid @RequestBody SampleDto sampleDto){
        Sample update = this.sampleDtoToSampleConverter.convert(sampleDto);
        Sample updatedSample = this.sampleService.update(sampleId, update);
        SampleDto updatedSampleDto = this.sampleToSampleDtoConverter.convert(updatedSample);
        return new Result(true, StatusCode.SUCCESS, "Update Success", updatedSampleDto);
    }

    @DeleteMapping("/{sampleId}")
    public Result deleteSample(@PathVariable String sampleId){
        this.sampleService.delete(sampleId);
        return new Result(true, StatusCode.SUCCESS, "Delete Success");
    }
    @GetMapping("/history/{sample_id}")
    public Result findSampleHistory(@PathVariable String sample_id) {
        Sample owner = this.sampleService.findById(sample_id);
        List<Entry> sampleHistory = owner.getSample_history();
        List<EntryDto> sampleHistoryDto = sampleHistory.stream()
                .map(this.entryToEntryDtoConverter::convert)
                .toList();
        return new Result(true, StatusCode.SUCCESS, "Found History", sampleHistoryDto);
    }



}
