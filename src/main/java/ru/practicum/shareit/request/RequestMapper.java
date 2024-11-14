package ru.practicum.shareit.request;

import ru.practicum.shareit.request.dto.ItemRequestDto;

public class RequestMapper {

    public ItemRequestDto toDto(ItemRequest itemRequest) {
        return ItemRequestDto.builder()
                .description(itemRequest.getDescription())
                .clientName(itemRequest.getClient().getName())
                .created(itemRequest.getCreated())
                .build();
    }
}
