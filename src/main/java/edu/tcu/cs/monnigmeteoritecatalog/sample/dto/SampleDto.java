package edu.tcu.cs.monnigmeteoritecatalog.sample.dto;

import jakarta.validation.constraints.NotEmpty;

public record SampleDto(String id,
                        @NotEmpty(message = "name is required") String name,
                        @NotEmpty(message = "monnig_number") String monnig_number,
                        @NotEmpty(message = "country is required") String country,
                        @NotEmpty(message = "class is required") String sample_class,
                        @NotEmpty(message = "group is required") String group,
                        @NotEmpty(message = "year found is required") String date_found_year,
                        @NotEmpty(message = "sample weight is required") Float sample_weight_g){
}
