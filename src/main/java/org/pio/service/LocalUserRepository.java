package org.pio.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.pio.model.User;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

/**
 * <Add description here>
 * <p/>
 * Created 2/10/16.
 */
@Service
public class LocalUserRepository implements UserRepository {

    // User which will always be in repository
    public static final String CONSTANT_USER = "pio";

    private Map<String, User> userRepository = Maps.newLinkedHashMap();

    public LocalUserRepository() {
        userRepository.put(CONSTANT_USER, new User(CONSTANT_USER, "Piotr", "W"));
        userRepository.put("aidan184", new User("aidan184", "Aidan", "W"));
        userRepository.put("vivi", new User("vivi", "Vivienne", "W"));
        userRepository.put("barbie", new User("barbie", "Barbie", "C"));
    }

    @Override
    public Optional<User> getUser(String userName) {
        return Optional.ofNullable(userRepository.get(userName));
    }

    @Override
    public User addUser(User user) {
        return userRepository.put(user.getUserName(), user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.values()
                .stream().collect(Collectors.toList());
    }


}
