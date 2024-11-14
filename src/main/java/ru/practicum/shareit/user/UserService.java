package ru.practicum.shareit.user;

import ru.practicum.shareit.exceptions.NotFoundDataException;
import ru.practicum.shareit.exceptions.NotNewEmail;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;

import java.util.List;

public interface UserService {

    User addUser(User user) throws NotNewEmail, NotFoundDataException;

    User updateUser(User user, long id) throws NotNewEmail, NotFoundDataException;

    User getUserById(long id) throws NotFoundDataException;

    List<UserDto> getAll();

    void deleteUser(long id) throws NotFoundDataException;
}
