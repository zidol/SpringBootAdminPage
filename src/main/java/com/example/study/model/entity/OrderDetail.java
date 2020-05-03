package com.example.study.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(exclude =  {"orderGroup", "item"})
//@ToString(exclude = {"user", "item"}) //user, item 객체를 상호참조 하고 있어서 lombok때문에 오버플로 생겨서 빼줘야 함
@EntityListeners(AuditingEntityListener.class)//감시자 리스너 사용하겠다는 설정 -> @CreatedBy, @LastModifiedBy 설정시
@Builder//생성자대신 사용할때
@Accessors(chain = true)
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private LocalDateTime arrivalDate;

    private Integer quantity;

    private BigDecimal totalPrice;

    //LoginUserAuditorAware에서 설정한 AdminServer가 들어감
    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    //LoginUserAuditorAware에서 설정한 AdminServer가 들어감
    @LastModifiedBy
    private String updatedBy;

    //OrderDetail N : 1 Item
    @ManyToOne
    private Item item;

    //OrderDetail N : 1 OrderGroup
    @ManyToOne
    private OrderGroup orderGroup;

    //자신 : 상대
    // N : 1
//    @ManyToOne
//    private User user; // 하이버네이트에서는 객체를 넣어줘야 하고 user_id를 찾아감
//
//    //N : 1
//    @ManyToOne
//    private Item item;
}
