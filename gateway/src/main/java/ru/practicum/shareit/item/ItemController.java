package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.CommentDescription;
import ru.practicum.shareit.item.dto.ItemDto;

@Controller
@RequestMapping("/items")
@Slf4j
@Validated
@RequiredArgsConstructor
public class ItemController {
    private final ItemClient itemClient;
    private static final String HEADER = "X-Sharer-User-Id";

    @PostMapping
    ResponseEntity<Object> add(@RequestBody ItemDto itemDto,
                               @RequestHeader(HEADER) long userId) {
        return itemClient.addItem(userId, itemDto);
    }

    @PatchMapping("/{id}")
    ResponseEntity<Object> update(@RequestBody ItemDto itemDto,
                                  @RequestHeader(HEADER) long userId,
                                  @PathVariable long id) {
        return itemClient.update(userId, itemDto, id);
    }

    @GetMapping("/{id}")
    ResponseEntity<Object> get(@PathVariable long id) {

        return itemClient.get(id);
    }

    @GetMapping
    ResponseEntity<Object> getUserItems(@RequestHeader(HEADER) long userId) {

        return itemClient.getUserItems(userId);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Object> delete(@RequestParam long id) {

        return itemClient.delete(id);
    }

    @GetMapping(value = "/search")
    ResponseEntity<Object> search(@RequestParam String text) {
        return itemClient.search(text);
    }

    @PostMapping(value = "/{idItem}/comment")
    ResponseEntity<Object> comment(@PathVariable long idItem,
                                   @RequestHeader(HEADER) long idUser,
                                   @RequestBody CommentDescription comment) {

        return itemClient.postComment(idItem, idUser, comment);
    }
}
