package edu.tcu.cs.monnigmeteoritecatalog.samplehistory;


import edu.tcu.cs.monnigmeteoritecatalog.samplehistory.converter.EntryDtoToEntryConverter;
import edu.tcu.cs.monnigmeteoritecatalog.samplehistory.converter.EntryToEntryDtoConverter;
import edu.tcu.cs.monnigmeteoritecatalog.samplehistory.dto.EntryDto;
import edu.tcu.cs.monnigmeteoritecatalog.system.Result;
import edu.tcu.cs.monnigmeteoritecatalog.system.StatusCode;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
We could remove findById and findAll I think, they may not be relevant
to the whole scheme.
 */


@RestController
@RequestMapping("/api/history")
public class EntryController {

    private final EntryService entryService;

    private final EntryToEntryDtoConverter entryToEntryDtoConverter;

    private final EntryDtoToEntryConverter entryDtoToEntryConverter;

    public EntryController(EntryService entryService, EntryToEntryDtoConverter entryToEntryDtoConverter, EntryDtoToEntryConverter entryDtoToEntryConverter) {
        this.entryService = entryService;
        this.entryToEntryDtoConverter = entryToEntryDtoConverter;
        this.entryDtoToEntryConverter = entryDtoToEntryConverter;
    }

    @GetMapping("/{entry_id}")
    public Result findEntryById(@PathVariable String entry_id) {
        Entry foundEntry = this.entryService.findById(entry_id);
        EntryDto entryDto = this.entryToEntryDtoConverter.convert(foundEntry);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", entryDto);
    }
    @GetMapping
    public Result findAllEntries() {
        List<Entry> foundEntries = this.entryService.findAll();
        List<EntryDto> entryDtos = foundEntries.stream()
                .map(this.entryToEntryDtoConverter::convert)
                .toList();
        return new Result(true, StatusCode.SUCCESS, "Find All Success", entryDtos);
    }
    @PostMapping
    public Result addEntry(@Valid @RequestBody EntryDto entryDto) {
        Entry newEntry = this.entryDtoToEntryConverter.convert(entryDto);
        assert newEntry != null;
        Entry savedEntry = this.entryService.save(newEntry);
        EntryDto savedEntryDto = this.entryToEntryDtoConverter.convert(savedEntry);
        return new Result(true, StatusCode.SUCCESS, "Add Success", savedEntryDto);
    }
    // Update entry ?
    @DeleteMapping("/{entry_id}")
    public Result deleteEntry(@PathVariable String entry_id) {
        this.entryService.delete(entry_id);
        return new Result(true, StatusCode.SUCCESS, "Delete Success");
    }

}
