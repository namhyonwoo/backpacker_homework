package com.backpacker.homework.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long uid;

    @Column
    String orderNumber;

    @Column
    Long ownerUid;

    @Column
    String productName;

    @Column
    LocalDateTime createdAt;


    @Builder
    public Order(String orderNumber, Long ownerUid, String productName) {
        this.orderNumber = orderNumber;
//        this.ownerUid = ownerUid;
        this.productName = productName;
        this.createdAt = LocalDateTime.now();
    }
}
