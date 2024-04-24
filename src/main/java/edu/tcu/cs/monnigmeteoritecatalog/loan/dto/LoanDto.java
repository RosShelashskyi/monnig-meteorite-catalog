package edu.tcu.cs.monnigmeteoritecatalog.loan.dto;

import edu.tcu.cs.monnigmeteoritecatalog.sample.Sample;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record LoanDto(String id,
                      List<String> samples_on_loan,
                      @NotEmpty(message = "name is required")
                      String loanee_name,
                      @NotEmpty(message = "email is required")
                      String loanee_email,
                      @NotEmpty(message = "institution is required")
                      String loanee_institution,
                      @NotEmpty(message = "address is required")
                      String loanee_address,
                      @NotEmpty(message = "start date required")
                      String loan_start_date,
                      @NotEmpty(message = "due date required")
                      String loan_due_date,
                      @NotEmpty(message = "notes required")
                      String loan_notes,
                      Boolean isArchived) {
}
