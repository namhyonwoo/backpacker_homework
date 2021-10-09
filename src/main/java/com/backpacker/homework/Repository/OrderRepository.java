package com.backpacker.homework.Repository;

import com.backpacker.homework.domain.Order;
import java.util.List;


public interface OrderRepository {
    List<Order> findByOwnerUid(Long ownerUid);
    Order save(Order order);
}
