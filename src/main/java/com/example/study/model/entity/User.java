package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor//기본 생성자 사용 x
@Entity
//ㅌㅔ이블 클래스 이름 동일하면 생략 가능
//@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //mysql => IDENTITY
    private Long id;

//    @Column(name = "account") 컬럼 명과 동일하다면 생략 가능
    private String account;

    private String email;

    private String phoneNumber;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;
    //자신 : orderDetail
    //1 : N
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")//orderDetail의 user와 똑같아야함
    private List<OrderDetail> orderDetailList;
}
