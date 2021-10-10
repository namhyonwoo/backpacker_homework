package com.backpacker.homework.controller.dto;


import com.backpacker.homework.domain.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MemberResponseDto {
    private Long uid;
    private String email;
    private String name;
    private String nickname;
    private String phone;
    private String password;
    private String gender;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Order order;

    public MemberResponseDto(BigInteger uid, String email, String name, String nickname, String phone, String password, Character gender, Timestamp createdAt, Timestamp updatedAt, BigInteger orderUid, String orderNumber, BigInteger orderOwnerUid, String orderProductName, Timestamp orderCreatedAt) {
        this.uid = uid.longValue();
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.phone = phone;
        this.password = password;
        this.gender = gender == null ? null : gender.toString();
        this.createdAt = createdAt.toLocalDateTime();
        this.updatedAt = updatedAt == null ? null : updatedAt.toLocalDateTime();

//        this.orderUid = orderUid == null ? null : orderUid.longValue();
//        this.orderNumber = orderNumber == null ? null : orderNumber;
//        this.orderOwnerUid = orderOwnerUid == null ? null : orderOwnerUid.longValue();
//        this.orderProductName = orderProductName == null ? null : orderProductName;
//        this.orderCreatedAt = orderCreatedAt == null ? null : orderCreatedAt.toLocalDateTime();

        if (orderUid != null) {
            this.order = Order.builder()
                    .uid(orderUid == null ? null : orderUid.longValue())
                    .orderNumber(orderNumber == null ? null : orderNumber)
                    .ownerUid(orderOwnerUid == null ? null : orderOwnerUid.longValue())
                    .productName(orderProductName == null ? null : orderProductName)
                    .createdAt(orderCreatedAt == null ? null : orderCreatedAt.toLocalDateTime())
                    .build();
        }
    }
}
