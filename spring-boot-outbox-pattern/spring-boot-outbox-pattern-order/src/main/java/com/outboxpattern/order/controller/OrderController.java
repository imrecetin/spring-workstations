package com.outboxpattern.order.controller;

import com.outboxpattern.order.model.Order;
import com.outboxpattern.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private OrderService orderService;

    @Autowired
    private OrderController(OrderService orderService){
        this.orderService=orderService;
    }

    @PostMapping
    public Order createOrder(@RequestBody Order orderRequest){
        return orderService.createOrder(orderRequest);
    }

}
