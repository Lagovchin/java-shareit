package ru.practicum.shareit.request;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;

/**
 * TODO Sprint add-item-requests.
 */

@Entity
@Table(name = "request")
@Data
@AllArgsConstructor
@Builder
public class ItemRequest {
    @Id
    private long id;
    private String description;
    @ManyToOne
    @JoinColumn(name = "requestor_id")
    private User client;
    private LocalDateTime created;
}
