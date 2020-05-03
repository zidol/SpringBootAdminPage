package com.example.study.model.entity;

import com.example.study.model.enumclass.UserStatus;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor//기본 생성자 사용 x
@Entity
//ㅌㅔ이블 클래스 이름 동일하면 생략 가능
//@Table(name = "user")
@ToString(exclude = {"orderGroupList"})
@EntityListeners(AuditingEntityListener.class)//감시자 리스너 사용하겠다는 설정 -> @CreatedBy, @LastModifiedBy 설정시
@Builder//생성자대신 사용할때
@Accessors(chain = true)    //update할때 (setPassword() 같은 set함수 사용할때)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //mysql => IDENTITY
    private Long id;

//    @Column(name = "account") 컬럼 명과 동일하다면 생략 가능
    private String account;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatus status;  //

    private String email;

    private String phoneNumber;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

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
    //자신 : orderDetail
    //1 : N
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")//orderDetail의 user와 똑같아야함
//    private List<OrderDetail> orderDetailList;

    // User 1 : N Ordrgroup
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<OrderGroup> orderGroupList;
}

