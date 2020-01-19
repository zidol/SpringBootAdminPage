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
        String account = "Test01";
        String password = "Test01";
        String status = "REGISTERED";
        String email = "Test01@gmail.com";
        String phoneNumber = "010-1111-2222";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);
        user.setCreatedAt(createdAt);
        user.setCreatedBy(createdBy);

        User newUser = userRepository.save(user);

        Assert.assertNotNull(newUser);
    }

    @Test
    @Transactional
    public void read() {
        //id type Long 이기 때문 2L
//        Optional<User> user = userRepository.findById(1L);
//        Optional<User> user = userRepository.findByAccount("TestUser04");

//        user.ifPresent(selectUser -> {
//            System.out.println("user : " + selectUser);
//
//            System.out.println("email : " + selectUser.getEmail());
//            selectUser.getOrderDetailList().stream().forEach(detail -> {
//                Item item = detail.getItem();
//                System.out.println(item);
//            });
//        });

        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");
        Assert.assertNotNull(user);
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
