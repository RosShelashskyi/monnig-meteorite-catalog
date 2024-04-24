package edu.tcu.cs.monnigmeteoritecatalog.loan;

import edu.tcu.cs.monnigmeteoritecatalog.loan.converter.LoanDtoToLoanConverter;
import edu.tcu.cs.monnigmeteoritecatalog.loan.converter.LoanToLoanDtoConverter;
import edu.tcu.cs.monnigmeteoritecatalog.loan.dto.LoanDto;
import edu.tcu.cs.monnigmeteoritecatalog.sample.Sample;
import edu.tcu.cs.monnigmeteoritecatalog.sample.SampleRepository;
import edu.tcu.cs.monnigmeteoritecatalog.sample.converter.SampleToSampleDtoConverter;
import edu.tcu.cs.monnigmeteoritecatalog.sample.dto.SampleDto;
import edu.tcu.cs.monnigmeteoritecatalog.samplehistory.Entry;
import edu.tcu.cs.monnigmeteoritecatalog.samplehistory.dto.EntryDto;
import edu.tcu.cs.monnigmeteoritecatalog.system.Result;
import edu.tcu.cs.monnigmeteoritecatalog.system.StatusCode;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.endpoint.base-url}/loan")
public class LoanController {
    private final LoanService loanService;

    private final LoanToLoanDtoConverter loanToLoanDtoConverter;

    private final LoanDtoToLoanConverter loanDtoToLoanConverter;

    private final SampleRepository sampleRepository;



    public LoanController(LoanService loanService, LoanToLoanDtoConverter loanToLoanDtoConverter, LoanDtoToLoanConverter loanDtoToLoanConverter, SampleRepository sampleRepository) {
        this.loanService = loanService;
        this.loanToLoanDtoConverter = loanToLoanDtoConverter;
        this.loanDtoToLoanConverter = loanDtoToLoanConverter;
        this.sampleRepository = sampleRepository;
    }

    @GetMapping("/view/{loanId}")
    public Result findLoanById(@PathVariable String loanId){
        Loan foundLoan = this.loanService.findById(loanId);
        LoanDto loanDto = this.loanToLoanDtoConverter.convert(foundLoan);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", loanDto);
    }

    @GetMapping("/all")
    public Result findAllLoans(){
        List<Loan> foundLoans = this.loanService.findAll();
        List<Loan> nonArchivedLoans = new ArrayList<>();
        for(Loan loan : foundLoans){
            if(!loan.isArchived()) {
                nonArchivedLoans.add(loan);
            }
        }
        List<LoanDto> loanDtos = nonArchivedLoans.stream()
                .map(this.loanToLoanDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find All Success", loanDtos);
    }

    @PostMapping("/create")
    public Result addLoan(@Valid @RequestBody LoanDto loanDto){
        Loan newLoan = this.loanDtoToLoanConverter.convert(loanDto);
        assert newLoan != null;
        Loan savedLoan = this.loanService.save(newLoan);
        LoanDto savedLoanDto = this.loanToLoanDtoConverter.convert(savedLoan);
        return new Result(true, StatusCode.SUCCESS, "Add Success", savedLoanDto);
    }

    @PutMapping("/update/{loanId}")
    public Result updateLoan(@PathVariable String loanId, @Valid @RequestBody LoanDto loanDto) {
        Loan update = this.loanDtoToLoanConverter.convert(loanDto);
        Loan updatedLoan = this.loanService.update(loanId, update);
        LoanDto updateLoanDto = this.loanToLoanDtoConverter.convert(updatedLoan);
        return new Result(true, StatusCode.SUCCESS, "Update Success", updateLoanDto);
    }
    @PostMapping("/archive/{loanId}")
    public Result archiveLoan(@PathVariable String loanId) {
        Loan loan = this.loanService.findById(loanId);
        loan.setArchived(true);
        this.loanService.update(loanId, loan);
        return new Result(true, StatusCode.SUCCESS, "Archive Success");
    }
    @PostMapping("/unarchive/{loanId}")
    public Result unArchiveLoan(@PathVariable String loanId) {
        Loan loan = this.loanService.findById(loanId);
        loan.setArchived(false);
        this.loanService.update(loanId, loan);
        return new Result(true, StatusCode.SUCCESS, "Unarchive Success");
    }
    // Returns a list of the monnig numbers for samples that are on the loan
    @GetMapping("/sample/all/{loanId}")
    public Result findSamplesOnLoan(@PathVariable String loanId) {
        Loan loan = this.loanService.findById(loanId);
        List<String> samples_on_loan = loan.getSamples_on_loan();
        return new Result(true, StatusCode.SUCCESS, "Found samples on loan", samples_on_loan);
    }
    @PutMapping("/{loanId}/samples/{sampleId}")
    public Result assignSample(@PathVariable String loanId, @PathVariable String sampleId) {
        this.loanService.assignSample(loanId, sampleId);
        return new Result(true, StatusCode.SUCCESS, "Sample assignment success");
    }
    @DeleteMapping("/delete/{loanId}")
    public Result deleteLoan(@PathVariable String loanId) {
        Loan loan = this.loanService.findById(loanId);
        List<String> samples_on_loan = loan.getSamples_on_loan();
        samples_on_loan.forEach(monnig -> this.sampleRepository.findByMonnigNumber(monnig).setLoan(null));
        this.loanService.delete(loanId); // delete the loan
        return new Result(true, StatusCode.SUCCESS, "Delete Success");
    }
    @GetMapping("/view/all/archived")
    public Result findAllArchivedLoans(){
        List<Loan> loans = this.loanService.findAll(); // get all the loans first
        List<Loan> archivedLoans = new ArrayList<>();
        for(Loan loan : loans) {
            if(loan.isArchived()){
                archivedLoans.add(loan);
            }
        }
        List<LoanDto> loanDtoList = archivedLoans.stream()
                .map(this.loanToLoanDtoConverter::convert)
                .toList();
        return new Result(true, StatusCode.SUCCESS, "Find All Success", loanDtoList);
    }
}
