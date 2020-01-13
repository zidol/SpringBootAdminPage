package com.example.study.repositoty;

import com.example.study.DemoApplicationTests;
import com.example.study.model.entity.OrderDetail;
import com.example.study.repository.OrderDetailRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public class OrderDetailRepositoryTest extends DemoApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create() {
        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setOrderAt(LocalDateTime.now());

        //어떤 사람?
//        orderDetail.setUserId(1L);

        //어떤 상품?
//        orderDetail.setItemId(1L);

        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
        Assert.assertNotNull(newOrderDetail);

    }

}
