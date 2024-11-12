package ru.practicum.shareit.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.exceptions.NotFoundDataException;
import ru.practicum.shareit.exceptions.NotNewEmail;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;

import java.util.List;

@Component
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserStorage userStorage;

    @Override
    public User addUser(User user) throws NotNewEmail, NotFoundDataException {
        return userStorage.addUser(user);
    }

    @Override
    public User updateUser(User user, long id) throws NotNewEmail, NotFoundDataException {
        return userStorage.updateUser(user, id);
    }

    @Override
    public User getUserById(long id) throws NotFoundDataException {
        return userStorage.getUserById(id);
    }

    @Override
    public List<UserDto> getAll() {
        return userStorage.getAll();
    }

    @Override
    public void deleteUser(long id) throws NotFoundDataException {
        userStorage.deleteUser(id);
    }
}
