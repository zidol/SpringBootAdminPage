package com.example.study.repositoty;

import com.example.study.DemoApplicationTests;
import com.example.study.model.entity.User;
import com.example.study.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends DemoApplicationTests {

    //Dependency Injection(DI)
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() {
        User user = new User();
        user.setAccount("TestUser04");
        user.setEmail("TestUser04@gmail.com");
        user.setPhoneNumber("010-4444-4444");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser4");

        User newUser = userRepository.save(user);
        System.out.println("NewUser : " + newUser);
    }

    @Test
    public void read() {
        //id type Long 이기 때문 2L
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser -> {
            System.out.println("user : " + selectUser);

            System.out.println("email : " + selectUser.getEmail());
        });
    }

    public void update() {

    }

    public void delete() {

    }
}
