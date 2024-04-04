package edu.tcu.cs.monnigmeteoritecatalog.samplehistory;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.security.Timestamp;

@Entity
public class SampleHistory implements Serializable {

    @Id
    private int sample_ID;
    // not 100% sure if this is what it means by timestamp
    private static Timestamp change_Date;
    private String change_Category;
    private String notes;

    /* following similar to what you did but for media? Not sure if this is correct
    @ManyToOne
    private Media media;
    */

    public SampleHistory() {
    }

    public int getSample_ID() {
        return sample_ID;
    }

    public void setSample_ID(int sample_ID) {
        this.sample_ID = sample_ID;
    }

    public static Timestamp getChange_Date() {
        return change_Date;
    }

    public static void setChange_Date(Timestamp change_Date) {
        SampleHistory.change_Date = change_Date;
    }

    public String getChange_Category() {
        return change_Category;
    }

    public void setChange_Category(String change_Category) {
        this.change_Category = change_Category;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}


