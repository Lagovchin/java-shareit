package ru.practicum.shareit.item.dto;

import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.practicum.shareit.comment.dto.CommentDto;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class ItemDtoResponse {

    private long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String available;
    private User owner;
    private LocalDateTime lastBooking;
    private LocalDateTime nextBooking;
    @Transient
    private List<CommentDto> comments;
}
