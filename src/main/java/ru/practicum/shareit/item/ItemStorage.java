package ru.practicum.shareit.item;

import ru.practicum.shareit.exceptions.NotFoundDataException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import java.util.List;

public interface ItemStorage {

    Item addItem(ItemDto itemDto, User user) throws NotFoundDataException;

    Item updateItem(long itemId, ItemDto itemDto) throws NotFoundDataException;

    Item getItemById(long id) throws NotFoundDataException;

    List<Item> getUserItems(long userId);

    void deleteItem(long id) throws NotFoundDataException;

    List<ItemDto> search(String searchString);
}
