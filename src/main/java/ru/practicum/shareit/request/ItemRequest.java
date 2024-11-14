package ru.practicum.shareit.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;

/**
 * TODO Sprint add-item-requests.
 */

@Data
@AllArgsConstructor
@Builder
public class ItemRequest {

    private long id;
    private String description;
    private User client;
    private LocalDateTime created;
}
