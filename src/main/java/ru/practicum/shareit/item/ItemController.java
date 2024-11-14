package ru.practicum.shareit.item;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.comment.CommentService;
import ru.practicum.shareit.comment.dto.CommentDto;
import ru.practicum.shareit.exceptions.NotFoundDataException;
import ru.practicum.shareit.exceptions.NotOwnerException;
import ru.practicum.shareit.exceptions.ValidationException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequestMapping("/items")
@AllArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final CommentService commentService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Item addItem(@RequestBody @Valid ItemDto itemDto,
                        @RequestHeader("X-Sharer-User-Id") long id) throws NotFoundDataException, ValidationException {
        return itemService.addItem(itemDto, id);
    }

    @PatchMapping(value = "/{idItem}")
    @ResponseStatus(HttpStatus.OK)
    public Item update(@RequestBody ItemDto itemDto, @RequestHeader("X-Sharer-User-Id") long idUser,
                       @PathVariable long idItem) throws NotFoundDataException, NotOwnerException {
        return itemService.update(itemDto, idUser, idItem);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Item getItemById(@PathVariable long id) throws NotFoundDataException {
        return itemService.getItemById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Item> getUserItems(@RequestHeader ("X-Sharer-User-Id") long userId) throws NotFoundDataException {
        return itemService.getUserItems(userId);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteItem(@PathVariable long id) throws NotFoundDataException {
        itemService.deleteItem(id);
    }

    @GetMapping(value = "/search")
    @ResponseStatus(HttpStatus.OK)
    public List<ItemDto> search(@RequestParam String text) {
        return itemService.search(text);
    }

    @PostMapping(value = "/{idItem}/comment")
    @ResponseStatus(HttpStatus.OK)
    public CommentDto postComment(@PathVariable long idItem,
                                  @RequestBody String text,
                                  @RequestHeader("X-Sharer-User-Id") long idUser) throws NotFoundDataException {
        return commentService.postComment(idItem, idUser, text);
    }


}
