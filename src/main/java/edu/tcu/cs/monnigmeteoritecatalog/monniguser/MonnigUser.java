package edu.tcu.cs.monnigmeteoritecatalog.monniguser;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;

@Entity
public class MonnigUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    @NotEmpty(message = "username is required")
    private String username;
    @NotEmpty(message = "password is required")
    private String password;
    private boolean enabled;

    @NotEmpty(message = "roles are required")
    private String roles;


    public MonnigUser(){

    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
