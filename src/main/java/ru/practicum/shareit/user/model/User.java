package ru.practicum.shareit.user.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * TODO Sprint add-controllers.
 */

@Data
public class User {

    private Long id;
    @NotBlank
    private String name;
    @Email
    @NotBlank
    private String email;
}