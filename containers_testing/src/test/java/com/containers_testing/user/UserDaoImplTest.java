package com.containers_testing.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserDaoImplTest {

    @Mock //it is already tested !!!
    private UserRepo userRepo;

    private UserDaoImpl userDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userDao = new UserDaoImpl(userRepo);
    }

    @Test
    void findById() {
        User user = new User("Boufnichel", "Boufnichel@gmail.com");
        user.setId(314L);

        when(userRepo.findById(314L)).thenReturn(Optional.of(user));

        Optional<User> result = userDao.findById(314L);

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    void findAll() {
        List<User> users = new ArrayList<>();
        users.add(new User("user1", "user1@example.com"));
        users.add(new User("user2", "user2@example.com"));

        when(userRepo.findAll()).thenReturn(users);

        List<User> result = userDao.findAll();

        assertEquals(users.size(), result.size());
        assertEquals(users, result);
    }

    @Test
    void save() {
        User user = new User("testUser", "test@example.com");

        userDao.save(user);
    }

    @Test
    void update_existingUser() {
        Long userId = 1L;
        User user = new User("testUser", "test@example.com");
        user.setId(userId);

        Optional<User> optionalUser = Optional.of(user);

        when(userRepo.findById(userId)).thenReturn(optionalUser);
        when(userRepo.save(any(User.class))).thenReturn(user);

        userDao.update(user);
    }

    @Test
    void update_nonExistingUser() {
        Long userId = 1L;
        User user = new User("testUser", "test@example.com");
        user.setId(userId);

        when(userRepo.findById(userId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> userDao.update(user));
    }

    @Test
    void delete() {
        User user = new User("testUser", "test@example.com");

        userDao.delete(user);

        verify(userRepo, times(1)).delete(user);
    }
}