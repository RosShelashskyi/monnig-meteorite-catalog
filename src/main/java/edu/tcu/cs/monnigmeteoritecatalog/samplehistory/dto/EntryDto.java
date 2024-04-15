package edu.tcu.cs.monnigmeteoritecatalog.samplehistory.dto;

import jakarta.validation.constraints.NotEmpty;

public record EntryDto(String id,
                       @NotEmpty(message = "date is required") String date,
                       @NotEmpty(message = "category is required") String category,
                       @NotEmpty(message = "notes is required") String notes,
                       @NotEmpty(message = "owner_id is required") String owner_id){
}
