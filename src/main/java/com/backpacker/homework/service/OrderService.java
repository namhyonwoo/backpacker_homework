package com.backpacker.homework.service;

import com.backpacker.homework.Repository.OrderRepository;
import com.backpacker.homework.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * 단일 회원의 주문목록 조회
     */
    public List<Order> findByOwnerUid(Long ownerUid){
        return orderRepository.findByOwnerUid(ownerUid);
    }

    public Long saveOrder(Order order){
        return orderRepository.save(order).getUid();
    }

}
