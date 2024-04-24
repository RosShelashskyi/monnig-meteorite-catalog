package edu.tcu.cs.monnigmeteoritecatalog.loan.converter;

import edu.tcu.cs.monnigmeteoritecatalog.loan.Loan;
import edu.tcu.cs.monnigmeteoritecatalog.loan.dto.LoanDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Component
public class LoanToLoanDtoConverter implements Converter<Loan, LoanDto> {
    public LoanToLoanDtoConverter() {
    }

    @Override
    public LoanDto convert(Loan source){
        return new LoanDto(source.getLoan_ID(),
                                        source.getSamples_on_loan(),
                                        source.getLoanee_name(),
                                        source.getLoanee_email(),
                                        source.getLoanee_institution(),
                                        source.getLoanee_address(),
                                        source.getLoan_start_date(),
                                        source.getLoan_due_date(),
                                        source.getLoan_notes(),
                                        source.isArchived());
    }

}
