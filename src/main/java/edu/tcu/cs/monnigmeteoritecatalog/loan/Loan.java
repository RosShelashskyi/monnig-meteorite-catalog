package edu.tcu.cs.monnigmeteoritecatalog.loan;

import edu.tcu.cs.monnigmeteoritecatalog.sample.Sample;
import jakarta.persistence.*;

import java.security.Timestamp;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Loan implements Serializable {

    @Id
    private String loan_ID;

    private String loanee_name;
    private String loanee_email;
    private String loanee_institution;
    private String loanee_address;
    private String loan_start_date;
    private String loan_due_date;
    private String loan_notes;
    private boolean isArchived;

    // holds the Monnig numbers of samples under a specific loan
    @ElementCollection
    private List<String> samples_on_loan = new ArrayList<>();



    public Loan() {

    }

    public String getLoan_ID() {
        return loan_ID;
    }

    public void setLoan_ID(String loan_ID) {
        this.loan_ID = loan_ID;
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

    public String getLoan_start_date() {
        return loan_start_date;
    }

    public void setLoan_start_date(String loan_start_date) {
        this.loan_start_date = loan_start_date;
    }

    public String getLoan_due_date() {
        return loan_due_date;
    }

    public void setLoan_due_date(String loan_due_date) {
        this.loan_due_date = loan_due_date;
    }

    public String getLoan_notes() {
        return loan_notes;
    }

    public void setLoan_notes(String loan_notes) {
        this.loan_notes = loan_notes;
    }

    public void removeAllSamplesFromLoan() {
        this.samples_on_loan = new ArrayList<>();
    }
    // Remove a specific monnig number from the list
    public void removeSampleFromLoan(String monnig_number) {
        this.samples_on_loan.remove(monnig_number);
    }

    public List<String> getSamples_on_loan() {
        return samples_on_loan;
    }

    public void setSamples_on_loan(List<String> samples_on_loan) {
        this.samples_on_loan = samples_on_loan;
    }
    public void addSample(String monnig_number) {
        samples_on_loan.add(monnig_number);
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }
}
