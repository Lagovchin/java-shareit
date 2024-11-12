package ru.practicum.shareit.item;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.shareit.exceptions.NotFoundDataException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class ItemMemoryStorage implements ItemStorage {

    private final Map<Long, Item> items;

    @Override
    public Item addItem(ItemDto itemDto, User user) throws NotFoundDataException {
        long id = generateId();
        id++;
        Item newItem = ItemMapper.fromDto(id, itemDto, user);
        items.put(newItem.getId(), newItem);
        return getItemById(newItem.getId());
    }

    @Override
    public Item updateItem(long itemId, ItemDto itemDto) throws NotFoundDataException {
        if (items.containsKey(itemId)) {
            Item oldItem = items.get(itemId);
            if (itemDto.getName() != null) {
                oldItem.setName(itemDto.getName());
                items.put(oldItem.getId(), oldItem);
            }
            if (itemDto.getDescription() != null) {
                oldItem.setDescription(itemDto.getDescription());
                items.put(oldItem.getId(), oldItem);
            }
            if (itemDto.getAvailable() != null) {
                oldItem.setAvailable(Boolean.parseBoolean(itemDto.getAvailable()));
                items.put(oldItem.getId(), oldItem);
            }
        } else {
            throw new NotFoundDataException("Вещь не найдена");
        }
        return getItemById(itemId);
    }

    @Override
    public Item getItemById(long id) throws NotFoundDataException {
        return items.values().stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundDataException("Вещь не найдена"));
    }

    @Override
    public List<Item> getUserItems(long userId) {
        return items.values().stream()
                .filter(i -> i.getOwner().getId() == userId)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteItem(long id) throws NotFoundDataException {
        if (items.containsKey(id)) {
            items.remove(id);
        } else {
            throw new NotFoundDataException("Вещь не найдена");
        }
    }

    @Override
    public List<ItemDto> search(String searchString) {

        List<ItemDto> result = new ArrayList<>();

        List<ItemDto> byName = items.values().stream()
                .filter(i -> i.getName().toLowerCase().equals(searchString))
                .filter(Item::isAvailable)
                .map(ItemMapper::toDto)
                .toList();

        List<ItemDto> byDescription = items.values().stream()
                .filter(i -> i.getDescription().toLowerCase().equals(searchString))
                .filter(Item::isAvailable)
                .map(ItemMapper::toDto)
                .toList();

        if (!byName.isEmpty()) {
            result.addAll(byName);
        }
        if (!byDescription.isEmpty()) {
            result.addAll(byDescription);
        }

        return result;
    }

    private long generateId() {
        return items.keySet().stream()
                .max((id1, id2) -> Math.toIntExact(id2 - id1))
                .orElse(1L);
    }
}
