package ru.practicum.shareit.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.practicum.shareit.booking.BookingStatus;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class BookingDtoResponse {
    private long id;
    private LocalDateTime start;
    private LocalDateTime end;
    private Item item;
    private User booker;
    private BookingStatus status;
}
