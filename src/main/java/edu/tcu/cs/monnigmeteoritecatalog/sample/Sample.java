package edu.tcu.cs.monnigmeteoritecatalog.sample;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.tcu.cs.monnigmeteoritecatalog.loan.Loan;
import jakarta.persistence.*;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import edu.tcu.cs.monnigmeteoritecatalog.samplehistory.Entry;

//class for representing meteorite samples
@Entity
public class Sample implements Serializable {

    //primary key
    @Id
    private String sample_ID;
    private String name;
    private String monnig_number;
    private String country;
    private String sample_class;
    private String sample_group;
    private String date_found_year;
    private float sample_weight_g;


    @ManyToOne
    private Loan loan;
    // Hold the sample history for this sample
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "sample")
    private List<Entry> sample_history = new ArrayList<>();

    public Sample() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSample_ID() {
        return sample_ID;
    }

    public void setSample_ID(String sample_ID) {
        this.sample_ID = sample_ID;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMonnig_number() {
        return monnig_number;
    }

    public void setMonnig_number(String monnig_number) {
        this.monnig_number = monnig_number;
    }

    public String getSample_class() {
        return sample_class;
    }

    public void setSample_class(String sample_class) {
        this.sample_class = sample_class;
    }

    public String getSample_group() {
        return sample_group;
    }

    public void setSample_group(String sample_group) {
        this.sample_group = sample_group;
    }

    public String getDate_found_year() {
        return date_found_year;
    }

    public void setDate_found_year(String date_found_year) {
        this.date_found_year = date_found_year;
    }

    public float getSample_weight_g() {
        return sample_weight_g;
    }

    public void setSample_weight_g(float sample_weight_g) {
        this.sample_weight_g = sample_weight_g;
    }

    public void setSample_history(List<Entry> sample_history) {
        this.sample_history = sample_history;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public List<Entry> getSample_history() {
        return sample_history;
    }
    public void addHistoryEntry(Entry historyEntry) {
        historyEntry.setSample(this);
        this.sample_history.add(historyEntry);
    }
    public void removeHistory() {
        this.sample_history.forEach(entry -> entry.setSample(null));
        this.sample_history = new ArrayList<>();
    }

}
