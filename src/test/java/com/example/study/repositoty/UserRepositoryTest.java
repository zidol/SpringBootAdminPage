package com.example.study.repositoty;

import com.example.study.DemoApplicationTests;
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
        String account = "Test03";
        String password = "Test03";
        String status = "REGISTERED";
        String email = "Test03@gmail.com";
        String phoneNumber = "010-1111-3333";
        LocalDateTime registeredAt = LocalDateTime.now();
//        LocalDateTime createdAt = LocalDateTime.now();
//        String createdBy = "AdminServer";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);

        //builder패턴(Lombok)
        User u = User.builder()
                .account(account)
                .password(password)
                .status(status)
                .email(email)
                .build();

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

        //////chain Pattern//////
//        user.setEmail("")
//                .setPhoneNumber("")
//                .setStatus("");
//        User u = new User().setAccount("").setEmail("").setPassword("");
        //////////////////////////
        if(user != null) {
            user.getOrderGroupList().stream().forEach(orderGroup -> {

                System.out.println("------------------주문 묶음-------------------");
                System.out.println("수령 인 : " + orderGroup.getRevName());
                System.out.println("수령 지 : " + orderGroup.getRevAddress());
                System.out.println("총금액 : " + orderGroup.getTotalPrice());
                System.out.println("총수량 : " + orderGroup.getTotalQuantity());
                System.out.println("------------------주문 상세-------------------");

                orderGroup.getOrderDetailList().forEach(orderDetail -> {
                    System.out.println("파트너사 이름 : " + orderDetail.getItem().getPartner().getName());
                    System.out.println("파트너사 카테고리  : " + orderDetail.getItem().getPartner().getCategory().getTitle());
                    System.out.println("주문 상품 : " + orderDetail.getItem().getName());
                    System.out.println("고객 센터 번호 : " + orderDetail.getItem().getPartner().getCallCenter());
                    System.out.println("주문의 상태 : " + orderDetail.getStatus());
                    System.out.println("도착 예정일자 : " + orderDetail.getArrivalDate());

                });

            });
        }
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
