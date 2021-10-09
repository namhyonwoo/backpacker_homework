package com.backpacker.homework.service;

import com.backpacker.homework.domain.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional //자동롤백
public class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Test
    @Commit
    public void 주문서_저장(){
        //given
        Order order = Order.builder()
                .orderNumber("asdbjsad123123")
                .productName("상품1")
                .build();

        //when
        Long saveId = orderService.saveOrder(order);

        System.out.println("saveId = "+saveId);

    }
}
