package ru.practicum.shareit.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.exceptions.NotFoundDataException;
import ru.practicum.shareit.exceptions.NotNewEmail;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Repository
public class UserMemoryStorage implements UserStorage{

    private Map<Long, User> users;
    private Set<String> emails;

    @Override
    public User addUser(User user) throws NotNewEmail, NotFoundDataException {
        if (isNewUser(user)) {
            if (isNewEmail(user)) {
                long id = generateId();
                id++;
                user.setId(id);
                users.put(user.getId(), user);
                emails.add(user.getEmail());
            }
        }
        return getUserById(user.getId());
    }

    @Override
    public User updateUser(User user, long id) throws NotNewEmail, NotFoundDataException {
        if (users.containsKey(id)) {
            user.setId(id);
            User userForUpdate = users.get(id);
            if (user.getEmail() == null && user.getName() != null) {
                userForUpdate.setName(user.getName());
                users.put(id, userForUpdate);
            } else if (user.getName() == null && user.getEmail() != null) {
                if (isNewEmail(user)) {
                    if (!emails.contains(user.getEmail())) {
                        userForUpdate.setEmail(user.getEmail());
                        users.put(id, userForUpdate);
                    } else {
                        throw new NotNewEmail("Указанный адрес электронной почты уже используется");
                    }
                }
            } else {
                if (isNewEmail(user)) {
                    if (!emails.contains(user.getEmail())) {
                        userForUpdate.setEmail(user.getEmail());
                        userForUpdate.setName(user.getName());
                        users.put(id, userForUpdate);
                    } else {
                        throw new NotNewEmail("Указанный адрес электронной почты уже используется");
                    }
                }
            }
        } else {
            throw new NotFoundDataException("Пользователь не найден");
        }
        return getUserById(id);
    }

    @Override
    public User getUserById(long id) throws NotFoundDataException {
        if (!users.containsKey(id)) {
            throw new NotFoundDataException("Пользователь не найден");
        }
        return users.get(id);
    }

    @Override
    public List<UserDto> getAll() {
        return users.values().stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(long id) throws NotFoundDataException {
        if (users.containsKey(id)) {
            users.remove(id);
        } else {
            throw new NotFoundDataException("Пользователь не найден");
        }

    }

    private boolean isNewUser(User user) {
        Optional<User> checkUser = users.values().stream()
                .filter(u -> u.equals(user))
                .findFirst();
        return checkUser.isEmpty();
    }

    private boolean isNewEmail(User user) throws NotNewEmail {
        Optional<User> checkUser = users.values().stream()
                .filter(u -> u.getEmail().equals(user.getEmail()))
                .findAny();
        if (checkUser.isPresent()) {
            throw new NotNewEmail("Указанный адрес электронной почты уже используется");
        }
        return true;
    }

    private long generateId() {
        return users.keySet().stream()
                .max((id1, id2) -> Math.toIntExact(id2 - id1))
                .orElse(1L);
    }
}
