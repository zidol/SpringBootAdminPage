package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@ToString(exclude = {"user", "item"}) //user, item 객체를 상호참조 하고 있어서 lombok때문에 오버플로 생겨서 빼줘야 함
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private LocalDateTime arrivalDate;

    private Integer quantity;

    private BigDecimal totalPrice;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    private Long itemId;

    private Long orderGroupId;

    //자신 : 상대
    // N : 1
//    @ManyToOne
//    private User user; // 하이버네이트에서는 객체를 넣어줘야 하고 user_id를 찾아감
//
//    //N : 1
//    @ManyToOne
//    private Item item;
}
