package ru.practicum.shareit.item;

import ru.practicum.shareit.exceptions.NotFoundDataException;
import ru.practicum.shareit.exceptions.NotOwnerException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemDtoResponse;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemService {

    Item addItem(ItemDto item, long id) throws NotFoundDataException;

    Item update(ItemDto itemDto, long userId, long itemId) throws NotFoundDataException, NotOwnerException;

    ItemDtoResponse getItemById(long id) throws NotFoundDataException;

    List<ItemDtoResponse> getUserItems(long userId) throws NotFoundDataException;

    List<ItemDto> search(String searchString);

    void delete(long id) throws NotFoundDataException;
}
