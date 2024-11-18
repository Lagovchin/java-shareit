package ru.practicum.shareit.item;

import ru.practicum.shareit.exceptions.NotFoundDataException;
import ru.practicum.shareit.exceptions.NotOwnerException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemDtoResponse;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemService {

    Item add(ItemDto itemDto, long id) throws NotFoundDataException;

    Item update(ItemDto itemDto, long idUser, long idItem) throws NotFoundDataException, NotOwnerException;

    ItemDtoResponse get(long id) throws NotFoundDataException;

    void delete(long id) throws NotFoundDataException;

    List<ItemDtoResponse> getUserItems(long id) throws NotFoundDataException;

    List<ItemDto> search(String searchString);

}
