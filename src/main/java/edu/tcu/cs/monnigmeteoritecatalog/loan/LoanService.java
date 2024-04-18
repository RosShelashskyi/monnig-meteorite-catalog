package edu.tcu.cs.monnigmeteoritecatalog.loan;


import edu.tcu.cs.monnigmeteoritecatalog.sample.Sample;
import edu.tcu.cs.monnigmeteoritecatalog.system.exception.ObjectNotFoundException;
import edu.tcu.cs.monnigmeteoritecatalog.utils.IdWorker;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class LoanService {
    private final LoanRepository loanRepository;

    private final IdWorker idWorker;

    public LoanService(LoanRepository loanRepository, IdWorker idWorker) {
        this.loanRepository = loanRepository;
        this.idWorker = idWorker;
    }

    public Loan findById(String loanId){
        return this.loanRepository.findById(loanId)
                .orElseThrow(() -> new ObjectNotFoundException("loan", loanId));
    }

    public List<Loan> findAll(){
        return this.loanRepository.findAll();
    }

    public Loan save(Loan newLoan){
        newLoan.setLoan_ID(idWorker.nextId() + "");
        //add the loan to each sample's loan list
        for(Sample sample : newLoan.getSamples_on_loan()){
            sample.addLoan(newLoan);
        }
        return this.loanRepository.save(newLoan);
    }

    public Loan update(String loanId, Loan update){
        return this.loanRepository.findById(loanId)
                .map(oldLoan -> {
                    oldLoan.setSamples_on_loan(update.getSamples_on_loan());
                    oldLoan.setLoanee_name(update.getLoanee_name());
                    oldLoan.setLoanee_email(update.getLoanee_email());
                    oldLoan.setLoanee_institution(update.getLoanee_institution());
                    oldLoan.setLoanee_address(update.getLoanee_address());
                    oldLoan.setLoan_start_date(update.getLoan_start_date());
                    oldLoan.setLoan_due_date(update.getLoan_due_date());
                    oldLoan.setLoan_notes(update.getLoan_notes());
                    return this.loanRepository.save(oldLoan);
                }).orElseThrow(() -> new ObjectNotFoundException("loan", loanId));
    }

    //there is no use case for deleting loans, only archiving them.
    //TODO: figure out how to handle archival
}
