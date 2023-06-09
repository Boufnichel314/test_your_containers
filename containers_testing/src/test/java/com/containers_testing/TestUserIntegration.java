package com.containers_testing;

import com.containers_testing.user.User;
import com.containers_testing.user.UserRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestUserIntegration extends TestContainer{

    @Autowired
    private UserRepo userRepository;

    @Test
    void testSaveUser() {
        User user = new User("Boufnichel", "Boufnichel@gmail.com");

        User savedUser = userRepository.save(user);

        List<User> users = userRepository.findAll();

        Assertions.assertThat(users).contains(savedUser);
    }

    @Test
    void testFindAllUsers() {
        User user1 = new User("Bouf1", "Bouf1@gamil.com");
        User user2 = new User("Bouf2", "bouf2@gmail.com");
        userRepository.save(user1);
        userRepository.save(user2);

        List<User> users = userRepository.findAll();

        System.out.println(users.get(1));

        Assertions.assertThat(users).hasSize(2);
    }

}
