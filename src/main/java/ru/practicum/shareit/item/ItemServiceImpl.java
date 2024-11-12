package ru.practicum.shareit.item;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.exceptions.NotFoundDataException;
import ru.practicum.shareit.exceptions.NotOwnerException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.UserService;
import ru.practicum.shareit.user.model.User;

import java.util.List;

@Component
@AllArgsConstructor
public class ItemServiceImpl implements ItemService{

    private final ItemStorage itemStorage;
    private final UserService userService;

    @Override
    public Item addItem(ItemDto itemDto, long id) throws NotFoundDataException {
        User user = userService.getUserById(id);
        return itemStorage.addItem(itemDto, user);
    }

    @Override
    public Item update(ItemDto itemDto, long userId, long itemId) throws NotFoundDataException, NotOwnerException {
        if (!ownerCheck(itemId, userId)) {
            throw new NotOwnerException("У вещи нет владельца");
        }
        return itemStorage.updateItem(itemId, itemDto);
    }

    @Override
    public Item getItemById(long id) throws NotFoundDataException {
        return itemStorage.getItemById(id);
    }

    @Override
    public List<Item> getUserItems(long userId) throws NotFoundDataException {
        if (userService.getUserById(userId) == null) {
            throw  new NotFoundDataException("Пользователь не найден");
        }
        return itemStorage.getUserItems(userId);
    }

    @Override
    public void deleteItem(long id) {

    }

    @Override
    public List<ItemDto> search(String searchString) {
        return itemStorage.search(searchString.toLowerCase());
    }

    private boolean ownerCheck(long userId, long itemId) throws NotFoundDataException {
        return itemStorage.getItemById(itemId).getOwner().getId() == userId;
    }
}
