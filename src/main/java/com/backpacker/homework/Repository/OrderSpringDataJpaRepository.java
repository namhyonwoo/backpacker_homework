package com.backpacker.homework.Repository;

import com.backpacker.homework.domain.Order;

import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderSpringDataJpaRepository extends JpaRepository<Order, Long>, OrderRepository {
}
