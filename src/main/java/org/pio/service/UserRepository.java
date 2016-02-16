package org.pio.service;

import java.util.List;
import java.util.Optional;

import org.pio.model.User;

import com.google.common.collect.Lists;

/**
 * <Add description here>
 * <p/>
 * Created 2/10/16.
 */
public interface UserRepository {
    default Optional<User> getUser(String userName) {
        throw new IllegalStateException("Not implemented");
    }

    default User addUser(User user) {
        throw new IllegalStateException("Not implemented");
    }

    default List<User> getAll() {
        return Lists.newArrayList();
    }
}
