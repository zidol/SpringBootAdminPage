package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(exclude = {"user", "item"})
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderAt;

    //자신 : 상대
    // N : 1
    @ManyToOne
    private User user; // 하이버네이트에서는 객체를 넣어줘야 하고 user_id를 찾아감

    //N : 1
    @ManyToOne
    private Item item;
}
