package com.backpacker.homework.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
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
    @Pattern(regexp = "^[A-Z0-9]{1,12}$")
    String orderNumber;

    @Column
    Long ownerUid;

    @Column
    String productName;

    @Column
    LocalDateTime createdAt;

    @Builder
    public Order(Long uid, String orderNumber, Long ownerUid, String productName, LocalDateTime createdAt) {
        this.uid = uid;
        this.orderNumber = orderNumber;
        this.ownerUid = ownerUid;
        this.productName = productName;
        this.createdAt = createdAt;
    }
}
