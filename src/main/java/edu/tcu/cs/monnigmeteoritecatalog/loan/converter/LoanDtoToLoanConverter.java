package edu.tcu.cs.monnigmeteoritecatalog.loan.converter;

import edu.tcu.cs.monnigmeteoritecatalog.loan.Loan;
import edu.tcu.cs.monnigmeteoritecatalog.loan.dto.LoanDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LoanDtoToLoanConverter implements Converter<LoanDto, Loan> {

    @Override
    public Loan convert(LoanDto source) {
        Loan loan = new Loan();
        loan.setLoan_ID(source.id());
        loan.setSamples_on_loan(source.samples_on_loan());
        loan.setLoanee_name(source.loanee_name());
        loan.setLoanee_email(source.loanee_email());
        loan.setLoanee_institution(source.loanee_institution());
        loan.setLoanee_address(source.loanee_address());
        loan.setLoan_start_date(source.loan_start_date());
        loan.setLoan_due_date(source.loan_due_date());
        loan.setLoan_notes(source.loan_notes());
        loan.setArchived(source.isArchived());
        return loan;
    }
}
