package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer price;

    private String content;

    //자신 : orderDetail
    // 1 : N
    // LAZY = 지연 로딩, EAGER = 즉시로딩
    // LAZY = select * from item where id = ?
    // EAGER = 1:1 , 혹은 한건만 있는 데이터만(모든 조인을 걸어서 모든 데이터를 조회 함
    // item_id = order_detail.item_id
    // user_id = order_detail_user_id
    // where item.id = ?
    // JOIN item item0_ left outer join order_detail orderdetai1_ on item0_.id=orderdetai1_.item_id left outer join user user2_ on orderdetai1_.user_id=user2_.id
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private List<OrderDetail> orderDetailList;

}
