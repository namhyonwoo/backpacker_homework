package com.backpacker.homework.controller;

import com.backpacker.homework.domain.Member;
import com.backpacker.homework.domain.Order;
import com.backpacker.homework.service.MemberService;
import com.backpacker.homework.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class OrderApiController {

    private final OrderService orderService;

    /**
     * 단일 회원의 주문목록 조회
     */
    @GetMapping("/orders")
    @ResponseBody
    public ResponseEntity<List<Order>> orders(@RequestParam(required = false) Long ownerUid) {

        return ResponseEntity
                .ok()
                .body(orderService.findByOwnerUid(ownerUid));
    }
}
