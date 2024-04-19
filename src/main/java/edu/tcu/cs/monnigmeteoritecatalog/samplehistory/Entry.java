package edu.tcu.cs.monnigmeteoritecatalog.samplehistory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.tcu.cs.monnigmeteoritecatalog.sample.Sample;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;

@Entity
public class Entry implements Serializable {

    @Id
    private String entry_id;
    // Just storing the date as a string, not sure if we need to store it as anything else following a format of
    // Month/Day/Year
    private String date;
    private String category;
    private String notes;
    // Sample
    @JsonIgnore
    @ManyToOne
    private Sample sample;

    public Entry() {

    }

    public String getEntry_id() {
        return entry_id;
    }

    public void setEntry_id(String entry_id) {
        this.entry_id = entry_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Sample getSample() {
        return sample;
    }

    public void setSample(Sample sample) {
        this.sample = sample;
    }
}


