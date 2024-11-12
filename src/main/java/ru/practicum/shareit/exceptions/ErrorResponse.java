package ru.practicum.shareit.exceptions;

import lombok.Data;

@Data
public class ErrorResponse {
    private final String errorMessage;
}
