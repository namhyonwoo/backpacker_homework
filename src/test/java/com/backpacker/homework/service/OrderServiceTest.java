package com.backpacker.homework.service;

import com.backpacker.homework.domain.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional //자동롤백
public class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Test
    public void 주문서_저장(){
        //given
        Order order = Order.builder()
                .orderNumber("ABC1234")
                .ownerUid(5L)
                .productName("상품23231")
                .build();

        //when
        Long saveId = orderService.saveOrder(order);

        System.out.println("saveId = "+saveId);

    }
}
