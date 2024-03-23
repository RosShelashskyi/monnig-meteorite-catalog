package edu.tcu.cs.monnigmeteoritecatalog.sample;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;
@Entity
public class Media implements Serializable {
    @Id
    private int sample_ID;
    private String filepath;
    private String category;
    private String file_ext;

    public Media () {

    }

    public int getSample_ID() {
        return sample_ID;
    }

    public void setSample_ID(int sample_ID) {
        this.sample_ID = sample_ID;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFile_ext() {
        return file_ext;
    }

    public void setFile_ext(String file_ext) {
        this.file_ext = file_ext;
    }
}
