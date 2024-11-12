package ru.practicum.shareit.user;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.exceptions.NotFoundDataException;
import ru.practicum.shareit.exceptions.NotNewEmail;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;

import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@AllArgsConstructor
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@Valid @RequestBody User user) throws NotNewEmail, NotFoundDataException {
        return userService.addUser(user);
    }

    @PatchMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@RequestBody User user, @PathVariable long id) throws NotNewEmail, NotFoundDataException {
        return userService.updateUser(user, id);
    }

    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable long id) throws NotFoundDataException {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable long id) throws NotFoundDataException {
        userService.deleteUser(id);
    }
}
