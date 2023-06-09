package com.containers_testing.user;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao{
    private final UserRepo userRepo;

    public UserDaoImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    @Override
    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }
    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }
    @Override
    public void save(User user) {
            userRepo.save(user);
    }
    @Override
    public void update(User user) {
        Optional<User> optionalUser = userRepo.findById(user.getId());
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());
            userRepo.save(existingUser);
        } else {
            throw new IllegalArgumentException("User with id " + user.getId() + " not found");
        }
    }
    @Override
    public void delete(User user) {
            userRepo.delete(user);
    }
}
