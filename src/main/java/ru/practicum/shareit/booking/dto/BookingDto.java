package ru.practicum.shareit.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * TODO Sprint add-bookings.
 */

@Data
@AllArgsConstructor
@Builder
public class BookingDto {
    private LocalDateTime start;
    private LocalDateTime end;
    private String item;
    private String owner;
    private String status;
}
