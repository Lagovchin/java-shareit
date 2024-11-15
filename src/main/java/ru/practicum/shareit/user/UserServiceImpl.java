package ru.practicum.shareit.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.exceptions.NotFoundDataException;
import ru.practicum.shareit.exceptions.NotNewEmail;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User addUser(User user) throws NotNewEmail, NotFoundDataException {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user, long id) throws NotNewEmail, NotFoundDataException {
        user.setId(id);
        User userDb = userRepository.findById(id).get();

        if (user.getEmail() == null) {
            user.setEmail(userDb.getEmail());
        } else if (user.getName() == null) {
            user.setName(userDb.getName());
        }

        return userRepository.save(user);
    }

    @Override
    public User getUserById(long id) throws NotFoundDataException {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new NotFoundDataException("Пользователь не найден");
        }
        return userOptional.get();
    }

    @Override
    public List<UserDto> getAll() {
        List<User> userList = userRepository.findAll();
        List<UserDto> userDtoList = userList.stream()
                .map(UserMapper::toDto)
                .toList();
        return userDtoList;
    }

    @Override
    public void deleteUser(long id) throws NotFoundDataException {
        userRepository.deleteById(id);
    }
}
