package edu.tcu.cs.monnigmeteoritecatalog.sample;

import edu.tcu.cs.monnigmeteoritecatalog.sample.converter.SampleDtoToSampleConverter;
import edu.tcu.cs.monnigmeteoritecatalog.sample.converter.SampleToSampleDtoConverter;
import edu.tcu.cs.monnigmeteoritecatalog.sample.dto.SampleDto;
import edu.tcu.cs.monnigmeteoritecatalog.samplehistory.Entry;
import edu.tcu.cs.monnigmeteoritecatalog.samplehistory.EntryService;
import edu.tcu.cs.monnigmeteoritecatalog.samplehistory.converter.EntryDtoToEntryConverter;
import edu.tcu.cs.monnigmeteoritecatalog.samplehistory.converter.EntryToEntryDtoConverter;
import edu.tcu.cs.monnigmeteoritecatalog.samplehistory.dto.EntryDto;
import edu.tcu.cs.monnigmeteoritecatalog.system.Result;
import edu.tcu.cs.monnigmeteoritecatalog.system.StatusCode;
import edu.tcu.cs.monnigmeteoritecatalog.system.exception.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.endpoint.base-url}/samples")
public class SampleController {
    private final SampleService sampleService;

    private final SampleToSampleDtoConverter sampleToSampleDtoConverter;

    private final SampleDtoToSampleConverter sampleDtoToSampleConverter;

    private final EntryToEntryDtoConverter entryToEntryDtoConverter;

    private final EntryDtoToEntryConverter entryDtoToEntryConverter;

    private final SampleRepository sampleRepository;

    private final EntryService entryService;

    public SampleController(SampleService sampleService, SampleToSampleDtoConverter sampleToSampleDtoConverter, SampleDtoToSampleConverter sampleDtoToSampleConverter, EntryToEntryDtoConverter entryToEntryDtoConverter, EntryDtoToEntryConverter entryDtoToEntryConverter, SampleRepository sampleRepository, EntryService entryService) {
        this.sampleService = sampleService;
        this.sampleToSampleDtoConverter = sampleToSampleDtoConverter;
        this.sampleDtoToSampleConverter = sampleDtoToSampleConverter;
        this.entryToEntryDtoConverter = entryToEntryDtoConverter;
        this.entryDtoToEntryConverter = entryDtoToEntryConverter;
        this.sampleRepository = sampleRepository;
        this.entryService = entryService;
    }
    @GetMapping("/view/{sampleId}")
    public Result findSampleById(@PathVariable String sampleId) {
        Sample foundSample = this.sampleService.findById(sampleId);
        SampleDto sampleDto = this.sampleToSampleDtoConverter.convert(foundSample);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", sampleDto);
    }
    @GetMapping("/related/{sampleId}")
    public Result findRelatedSamplesById(@PathVariable String sampleId) {
        Sample sample = this.sampleService.findById(sampleId); // main sample we are looking for relations
        String majorMonnig = sample.getMonnig_number().substring(0, sample.getMonnig_number().indexOf('.')); // this should reformat the M123.1 -> M123
        List<Sample> foundSamples = this.sampleRepository.findByMonnigMajorNum(majorMonnig);
        List<SampleDto> foundSampleDtos = foundSamples.stream()
                .map(this.sampleToSampleDtoConverter::convert)
                .toList();
        return new Result(true, StatusCode.SUCCESS, "Find related samples success", foundSampleDtos);
    }
    @GetMapping("/view/all")
    public Result findAllSamples() {
        List<Sample> foundSamples = this.sampleService.findAll();
        List<SampleDto> sampleDtos = foundSamples.stream()
                .map(this.sampleToSampleDtoConverter::convert)
                .toList();
        return new Result(true, StatusCode.SUCCESS, "Find All Success", sampleDtos);
    }
    @PostMapping("/add")
    public Result addSample(@Valid @RequestBody SampleDto sampleDto){
        Sample newSample = this.sampleDtoToSampleConverter.convert(sampleDto);
        assert newSample != null;
        Sample savedSample = this.sampleService.save(newSample);
        SampleDto savedSampleDto = this.sampleToSampleDtoConverter.convert(savedSample);
        return new Result(true, StatusCode.SUCCESS, "Add Success", savedSampleDto);

    }

    @PutMapping("/update/{sampleId}")
    public Result updateSample(@PathVariable String sampleId, @Valid @RequestBody SampleDto sampleDto){
        Sample update = this.sampleDtoToSampleConverter.convert(sampleDto);
        Sample updatedSample = this.sampleService.update(sampleId, update);
        SampleDto updatedSampleDto = this.sampleToSampleDtoConverter.convert(updatedSample);
        return new Result(true, StatusCode.SUCCESS, "Update Success", updatedSampleDto);
    }

    @DeleteMapping("/delete/{sampleId}")
    public Result deleteSample(@PathVariable String sampleId){
        this.sampleService.delete(sampleId);
        return new Result(true, StatusCode.SUCCESS, "Delete Success");
    }
    @GetMapping("/history/all/{sampleId}")
    public Result findSampleHistory(@PathVariable String sampleId) {
        Sample sample = this.sampleService.findById(sampleId);
        List<Entry> sampleHistory = sample.getSample_history();
        List<EntryDto> sampleHistoryDto = sampleHistory.stream()
                .map(this.entryToEntryDtoConverter::convert)
                .toList();
        return new Result(true, StatusCode.SUCCESS, "Found History", sampleHistoryDto);
    }
    @PostMapping("/history/add/{sampleId}")
    public Result addEntry(@PathVariable String sampleId, @Valid @RequestBody EntryDto entryDto){
        Sample sample = this.sampleRepository.findById(sampleId).orElseThrow(() -> new ObjectNotFoundException("sample", sampleId)); // owner
        Entry newEntry = this.entryDtoToEntryConverter.convert(entryDto); // get the new entry
        assert newEntry != null;
        newEntry.setSample(sample); // set the owner
        Entry savedEntry = this.entryService.save(newEntry);
        EntryDto savedEntryDto = this.entryToEntryDtoConverter.convert(savedEntry);
        return new Result(true, StatusCode.SUCCESS, "Entry Created", savedEntryDto);
    }
//    @PostMapping("/subsample/{sampleId}")
//    public Result addSubsample(@PathVariable String sampleId, @Valid @RequestBody SampleDto sampleDto){
//        Sample sample = this.sampleDtoToSampleConverter.convert(sampleDto);
//        Sample newSubSample = this.sampleService.subsample(sampleId, sample);
//        SampleDto newSubSampleDto = this.sampleToSampleDtoConverter.convert(newSubSample);
//        return new Result(true, StatusCode.SUCCESS, "Subsample Created", newSubSampleDto);
//    }


}
