package com.containers_testing.user;

import java.util.List;
import java.util.Optional;

//  A simple interface handling some crud methods
public interface UserDao {
    Optional<User> findById(Long id);

    List<User> findAll();

    void save(User user);

    void update(User user);

    void delete(User user);
}