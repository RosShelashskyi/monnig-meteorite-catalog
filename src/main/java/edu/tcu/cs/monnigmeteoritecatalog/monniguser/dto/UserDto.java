package edu.tcu.cs.monnigmeteoritecatalog.monniguser.dto;

import jakarta.validation.constraints.NotEmpty;

public record UserDto(Integer Id, @NotEmpty(message = "username is required") String username, boolean enabled,@NotEmpty(message = "roles are required") String roles) {
}
