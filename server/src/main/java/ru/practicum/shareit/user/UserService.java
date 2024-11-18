package ru.practicum.shareit.user;

import ru.practicum.shareit.exceptions.NotFoundDataException;
import ru.practicum.shareit.user.dto.UserDto;

import java.util.List;

public interface UserService {

    User add(User user);

    User update(User user, long id);

    User get(long id) throws NotFoundDataException;

    List<UserDto> getAll();

    void delete(long id);
}
