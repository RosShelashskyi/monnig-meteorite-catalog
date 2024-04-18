package edu.tcu.cs.monnigmeteoritecatalog.monnigcurator.dto;

import jakarta.validation.constraints.NotEmpty;

public record CuratorDto(Integer Id, @NotEmpty(message = "username is required") String username, boolean enabled, @NotEmpty(message = "password is required") String password, String roles) {
}
