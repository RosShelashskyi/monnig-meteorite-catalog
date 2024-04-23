package edu.tcu.cs.monnigmeteoritecatalog.loan;

import edu.tcu.cs.monnigmeteoritecatalog.loan.converter.LoanDtoToLoanConverter;
import edu.tcu.cs.monnigmeteoritecatalog.loan.converter.LoanToLoanDtoConverter;
import edu.tcu.cs.monnigmeteoritecatalog.loan.dto.LoanDto;
import edu.tcu.cs.monnigmeteoritecatalog.system.Result;
import edu.tcu.cs.monnigmeteoritecatalog.system.StatusCode;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.endpoint.base-url}/loan")
public class LoanController {
    private final LoanService loanService;

    private final LoanToLoanDtoConverter loanToLoanDtoConverter;

    private final LoanDtoToLoanConverter loanDtoToLoanConverter;

    public LoanController(LoanService loanService, LoanToLoanDtoConverter loanToLoanDtoConverter, LoanDtoToLoanConverter loanDtoToLoanConverter) {
        this.loanService = loanService;
        this.loanToLoanDtoConverter = loanToLoanDtoConverter;
        this.loanDtoToLoanConverter = loanDtoToLoanConverter;
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
        List<LoanDto> loanDtos = foundLoans.stream()
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
}
