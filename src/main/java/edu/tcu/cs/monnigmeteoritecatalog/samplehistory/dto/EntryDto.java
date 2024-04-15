package edu.tcu.cs.monnigmeteoritecatalog.samplehistory.dto;

import edu.tcu.cs.monnigmeteoritecatalog.sample.dto.SampleDto;
import jakarta.validation.constraints.NotEmpty;

public record EntryDto(String id,
                       @NotEmpty(message = "date is required") String date,
                       @NotEmpty(message = "category is required") String category,
                       @NotEmpty(message = "notes is required") String notes,
                       SampleDto owner){
}
