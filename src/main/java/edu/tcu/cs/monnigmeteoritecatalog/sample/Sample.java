package edu.tcu.cs.monnigmeteoritecatalog.sample;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import edu.tcu.cs.monnigmeteoritecatalog.sample.SampleHistory;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

//class for representing meteorite samples
@Entity
public class Sample implements Serializable {

    //primary key
    @Id
    private String sample_ID;
    private String monnig_number;
    private boolean is_type_specimen;
    private boolean is_educational;
    private float sample_weight_g;
    private String sample_format;
    private String sample_format_notes;
    private String name;
    private String country;
    private String location;

    //maybe date can be like an object containing all these fields?
    //I'll leave it like this for now, but something to consider
    private String date_found_year;
    private String date_found_month;
    private String date_found_day;
    private String date_found_hour;
    private boolean observed_fall;

    //the original variable name is class, but that a reserved work lol
    private String sample_class;
    private String clan;
    private String group;
    private String type;
    private float total_known_weight_num;
    private String total_know_weight_units;

    @ManyToOne
    private SampleHistory history;

    public Sample() {
    }

    public String getSampleId() {
        return sample_ID;
    }

    public void setSampleId(String sampleId) {
        this.sample_ID = sampleId;
    }

    public String getMonnigNumber() {
        return monnig_number;
    }

    public void setMonnigNumber(String monnigNumber) {
        this.monnig_number = monnigNumber;
    }

    public boolean isIs_type_specimen() {
        return is_type_specimen;
    }

    public void setIs_type_specimen(boolean is_type_specimen) {
        this.is_type_specimen = is_type_specimen;
    }

    public boolean isIs_educational() {
        return is_educational;
    }

    public void setIs_educational(boolean is_educational) {
        this.is_educational = is_educational;
    }

    public float getSample_weight_g() {
        return sample_weight_g;
    }

    public void setSample_weight_g(float sample_weight_g) {
        this.sample_weight_g = sample_weight_g;
    }

    public String getSample_format() {
        return sample_format;
    }

    public void setSample_format(String sample_format) {
        this.sample_format = sample_format;
    }

    public String getSample_format_notes() {
        return sample_format_notes;
    }

    public void setSample_format_notes(String sample_format_notes) {
        this.sample_format_notes = sample_format_notes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate_found_year() {
        return date_found_year;
    }

    public void setDate_found_year(String date_found_year) {
        this.date_found_year = date_found_year;
    }

    public String getDate_found_month() {
        return date_found_month;
    }

    public void setDate_found_month(String date_found_month) {
        this.date_found_month = date_found_month;
    }

    public String getDate_found_day() {
        return date_found_day;
    }

    public void setDate_found_day(String date_found_day) {
        this.date_found_day = date_found_day;
    }

    public String getDate_found_hour() {
        return date_found_hour;
    }

    public void setDate_found_hour(String date_found_hour) {
        this.date_found_hour = date_found_hour;
    }

    public boolean isObserved_fall() {
        return observed_fall;
    }

    public void setObserved_fall(boolean observed_fall) {
        this.observed_fall = observed_fall;
    }

    public String getSample_class() {
        return sample_class;
    }

    public void setSample_class(String sample_class) {
        this.sample_class = sample_class;
    }

    public String getClan() {
        return clan;
    }

    public void setClan(String clan) {
        this.clan = clan;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getTotal_known_weight_num() {
        return total_known_weight_num;
    }

    public void setTotal_known_weight_num(float total_known_weight_num) {
        this.total_known_weight_num = total_known_weight_num;
    }

    public String getTotal_know_weight_units() {
        return total_know_weight_units;
    }

    public void setTotal_know_weight_units(String total_know_weight_units) {
        this.total_know_weight_units = total_know_weight_units;
    }
}
