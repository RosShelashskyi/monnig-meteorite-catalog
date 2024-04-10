package edu.tcu.cs.monnigmeteoritecatalog.loan;

import edu.tcu.cs.monnigmeteoritecatalog.sample.Sample;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.security.Timestamp;
import java.io.Serializable;
import java.util.List;

@Entity
public class Loan implements Serializable {

    @Id
    private int loan_ID;

    @OneToMany
    private List<Sample> samples_on_loan;

    private String loanee_name;
    private String loanee_email;
    private String loanee_institution;
    private String loanee_address;
    private Timestamp loan_start_date;
    private Timestamp loan_due_date;
    private String loan_notes;

    public Loan() {

    }

    public int getLoan_ID() {
        return loan_ID;
    }

    public void setLoan_ID(int loan_ID) {
        this.loan_ID = loan_ID;
    }

    public List<Sample> getSamples_on_loan() {
        return samples_on_loan;
    }

    public void setSamples_on_loan(List<Sample> samples_on_loan) {
        this.samples_on_loan = samples_on_loan;
    }

    public String getLoanee_name() {
        return loanee_name;
    }

    public void setLoanee_name(String loanee_name) {
        this.loanee_name = loanee_name;
    }

    public String getLoanee_email() {
        return loanee_email;
    }

    public void setLoanee_email(String loanee_email) {
        this.loanee_email = loanee_email;
    }

    public String getLoanee_institution() {
        return loanee_institution;
    }

    public void setLoanee_institution(String loanee_institution) {
        this.loanee_institution = loanee_institution;
    }

    public String getLoanee_address() {
        return loanee_address;
    }

    public void setLoanee_address(String loanee_address) {
        this.loanee_address = loanee_address;
    }

    public Timestamp getLoan_start_date() {
        return loan_start_date;
    }

    public void setLoan_start_date(Timestamp loan_start_date) {
        this.loan_start_date = loan_start_date;
    }

    public Timestamp getLoan_due_date() {
        return loan_due_date;
    }

    public void setLoan_due_date(Timestamp loan_due_date) {
        this.loan_due_date = loan_due_date;
    }

    public String getLoan_notes() {
        return loan_notes;
    }

    public void setLoan_notes(String loan_notes) {
        this.loan_notes = loan_notes;
    }
}
