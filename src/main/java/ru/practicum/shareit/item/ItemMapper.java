package ru.practicum.shareit.item;

import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemDtoResponse;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

public class ItemMapper {

    public static ItemDto toDto(Item item) {
        return ItemDto.builder()
                .name(item.getName())
                .description(item.getDescription())
                .available(String.valueOf(item.isAvailable()))
                .request(item.getRequest() != null ? item.getRequest().getId() : 0)
                .build();
    }

    public static Item fromDto(ItemDto itemDto, User user) {

        return Item.builder()
                .name(itemDto.getName())
                .description(itemDto.getDescription())
                .available(Boolean.parseBoolean(itemDto.getAvailable()))
                .owner(user)
                .request(null)
                .build();
    }

    public static Item fromDtoWithDates(ItemDtoResponse itemDtoWithBookingDates, User owner) {
        return Item.builder()
                .name(itemDtoWithBookingDates.getName())
                .description(itemDtoWithBookingDates.getDescription())
                .available(Boolean.parseBoolean(itemDtoWithBookingDates.getAvailable()))
                .owner(owner)
                .request(null)
                .build();
    }

    public static ItemDtoResponse toDtoWithBookingDates(Item item) {

        return ItemDtoResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(String.valueOf(item.isAvailable()))
                .owner(item.getOwner())
                .build();
    }
}
