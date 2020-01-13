package com.example.study.repositoty;

import com.example.study.DemoApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;
import com.example.study.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
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
    @Transactional
    public void read() {
        //id type Long 이기 때문 2L
        Optional<User> user = userRepository.findById(1L);

        user.ifPresent(selectUser -> {
//            System.out.println("user : " + selectUser);
//
//            System.out.println("email : " + selectUser.getEmail());
            selectUser.getOrderDetailList().stream().forEach(detail -> {
                Item item = detail.getItem();
                System.out.println(item);
            });
        });
    }

    @Test
    public void update() {
        Optional<User> user = userRepository.findById(2L);
        user.ifPresent(selectUser -> {
           selectUser.setAccount("ppp");;
           selectUser.setUpdatedAt(LocalDateTime.now());
           selectUser.setUpdatedBy("update method()");
           userRepository.save(selectUser);
        });
    }

    @Test
    @Transactional//테스트 실행 후 다시 롤백 해줌
    public void delete() {
        Optional<User> user = userRepository.findById(3L);
        //test 코드로 할때
        Assert.assertTrue(user.isPresent()); // true

        user.ifPresent(selectUser -> {
            userRepository.delete(selectUser);
        });
        Optional<User> deleteUser = userRepository.findById(3L);

//        if(deleteUser.isPresent()) {
//            //user가 존재 할때
//            System.out.println("데이터 존재" + deleteUser.get());
//        } else {
//            //존재 하지 않을떄
//            System.out.println("데이터 삭제 데이터 없음");
//        }
        Assert.assertFalse(deleteUser.isPresent());//false
    }
}
