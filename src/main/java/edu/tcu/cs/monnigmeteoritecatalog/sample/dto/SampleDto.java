package edu.tcu.cs.monnigmeteoritecatalog.sample.dto;

import edu.tcu.cs.monnigmeteoritecatalog.loan.Loan;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record SampleDto(String id,
                        @NotEmpty(message = "name is required") String name,
                        @NotEmpty(message = "monnig_number") String monnig_number,
                        @NotEmpty(message = "country is required") String country,
                        @NotEmpty(message = "class is required") String sample_class,
                        @NotEmpty(message = "group is required") String group,
                        @NotEmpty(message = "year found is required") String date_found_year,
                        @NotNull(message = "sample weight is required") Float sample_weight_g,
                        List<Loan> loans){
}
