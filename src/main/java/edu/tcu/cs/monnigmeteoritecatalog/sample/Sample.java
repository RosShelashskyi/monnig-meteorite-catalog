package edu.tcu.cs.monnigmeteoritecatalog.sample;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import edu.tcu.cs.monnigmeteoritecatalog.samplehistory.SampleHistory;
import jakarta.persistence.ManyToOne;

import java.io.File;
import java.io.Serializable;
import java.util.List;

//class for representing meteorite samples
@Entity
public class Sample implements Serializable {

    //primary key
    @Id
    private String sample_ID;
    private String name;
    private String monnig_number;
    private String sample_class;
    private String group;
    private String clan;
    private String country;
    private String location;
    private String found_info;
    private String type;
    private float total_known_weight_num;
    private String total_know_weight_units;
    private float sample_weight_g;
    private String date_found_year;
    private String date_found_month;
    private String date_found_day;
    private String date_found_hour;
    private boolean is_educational;
    private boolean is_repository;
    private String sample_format;
    private String external_resources;
    @ElementCollection
    private List<File> images;
    private String additional_class_info;

    @ManyToOne
    private SampleHistory history;

    public Sample() {
    }

    public String getSample_ID() {
        return sample_ID;
    }

    public void setSample_ID(String sample_ID) {
        this.sample_ID = sample_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getClan() {
        return clan;
    }

    public void setClan(String clan) {
        this.clan = clan;
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

    public String getFound_info() {
        return found_info;
    }

    public void setFound_info(String found_info) {
        this.found_info = found_info;
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

    public float getSample_weight_g() {
        return sample_weight_g;
    }

    public void setSample_weight_g(float sample_weight_g) {
        this.sample_weight_g = sample_weight_g;
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

    public boolean isIs_educational() {
        return is_educational;
    }

    public void setIs_educational(boolean is_educational) {
        this.is_educational = is_educational;
    }

    public boolean isIs_repository() {
        return is_repository;
    }

    public void setIs_repository(boolean is_repository) {
        this.is_repository = is_repository;
    }

    public String getSample_format() {
        return sample_format;
    }

    public void setSample_format(String sample_format) {
        this.sample_format = sample_format;
    }

    public String getExternal_resources() {
        return external_resources;
    }

    public void setExternal_resources(String external_resources) {
        this.external_resources = external_resources;
    }

    public List<File> getImages() {
        return images;
    }

    public void setImages(List<File> images) {
        this.images = images;
    }

    public String getAdditional_class_info() {
        return additional_class_info;
    }

    public void setAdditional_class_info(String additional_class_info) {
        this.additional_class_info = additional_class_info;
    }

    public SampleHistory getHistory() {
        return history;
    }

    public void setHistory(SampleHistory history) {
        this.history = history;
    }
}
