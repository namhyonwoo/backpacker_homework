package com.backpacker.homework.controller;

import com.backpacker.homework.domain.Order;
import com.backpacker.homework.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
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
    @Operation(summary = "단일 회원의 주문목록 조회", description = "특정 회읜의 고유번호로 주문했던 목록을 조회한다.")
    public ResponseEntity<List<Order>> orders(@RequestParam(required = false) Long ownerUid) {

        return ResponseEntity
                .ok()
                .body(orderService.findByOwnerUid(ownerUid));
    }
}
