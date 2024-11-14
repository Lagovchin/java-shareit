package ru.practicum.shareit.item;

import ru.practicum.shareit.exceptions.NotFoundDataException;
import ru.practicum.shareit.exceptions.NotOwnerException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemService {

    Item addItem(ItemDto item, long id) throws NotFoundDataException;

    Item update(ItemDto itemDto, long userId, long itemId) throws NotFoundDataException, NotOwnerException;

    Item getItemById(long id) throws NotFoundDataException;

    List<Item> getUserItems(long userId) throws NotFoundDataException;

    void deleteItem(long id);

    List<ItemDto> search(String searchString);
}
