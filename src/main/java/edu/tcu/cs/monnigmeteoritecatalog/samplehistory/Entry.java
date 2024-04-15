package edu.tcu.cs.monnigmeteoritecatalog.samplehistory;

import edu.tcu.cs.monnigmeteoritecatalog.sample.Sample;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Entity
public class Entry implements Serializable {

    @Id
    private String entry_id;
    // Just storing the date as a string, not sure if we need to store it as anything else following a format of
    // Month/Day/Year
    private String date;
    private String category;
    private String notes;


    // One sample can have many different entries, all of these entries combined make up the "Sample History"
    @ManyToOne
    private Sample owner;

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

    public Sample getOwner() {
        return owner;
    }

    public void setOwner(Sample owner) {
        this.owner = owner;
    }
}


