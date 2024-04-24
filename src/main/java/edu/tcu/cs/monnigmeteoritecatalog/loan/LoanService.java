package edu.tcu.cs.monnigmeteoritecatalog.loan;


import edu.tcu.cs.monnigmeteoritecatalog.sample.Sample;
import edu.tcu.cs.monnigmeteoritecatalog.sample.SampleService;
import edu.tcu.cs.monnigmeteoritecatalog.samplehistory.Entry;
import edu.tcu.cs.monnigmeteoritecatalog.system.exception.ObjectNotFoundException;
import edu.tcu.cs.monnigmeteoritecatalog.utils.IdWorker;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class LoanService {
    private final LoanRepository loanRepository;

    private final SampleService sampleService;

    private final IdWorker idWorker;

    public LoanService(LoanRepository loanRepository, SampleService sampleService, IdWorker idWorker) {
        this.loanRepository = loanRepository;
        this.sampleService = sampleService;
        this.idWorker = idWorker;
    }

    public Loan findById(String loanId){
        return this.loanRepository.findById(loanId)
                .orElseThrow(() -> new ObjectNotFoundException("loan", loanId));
    }

    public List<Loan> findAll(){
        return this.loanRepository.findAll();
    }

    public Loan save(Loan newLoan) {
        newLoan.setLoan_ID(idWorker.nextId() + "");
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
                    oldLoan.setArchived(update.isArchived());
                    return this.loanRepository.save(oldLoan);
                }).orElseThrow(() -> new ObjectNotFoundException("loan", loanId));
    }
    public void delete(String loanId){
        Loan loanToBeDeleted = this.loanRepository.findById(loanId)
                .orElseThrow(() -> new ObjectNotFoundException("loan", loanId));
        this.loanRepository.deleteById(loanId);
    }
    public void assignSample(String loanId, String sampleId){
        Sample sampleToBeAssigned = this.sampleService.findById(sampleId);
        Loan loan = this.loanRepository.findById(loanId).orElseThrow(() -> new ObjectNotFoundException("loan", loanId));
        if(sampleToBeAssigned.getLoan() != null){
            sampleToBeAssigned.getLoan().removeSampleFromLoan(sampleToBeAssigned.getMonnig_number());
        }
        sampleToBeAssigned.setLoan(loan);
        loan.addSample(sampleToBeAssigned.getMonnig_number());
    }

    //there is no use case for deleting loans, only archiving them.
    //TODO: figure out how to handle archival
}
