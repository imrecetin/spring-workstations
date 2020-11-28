package com.outboxpattern.order.service;

import com.outboxpattern.order.model.Order;
import com.outboxpattern.order.outbox.OutboxEventPublisher;
import com.outboxpattern.order.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;

@Service
@Slf4j
public class OrderService {

    private OrderRepository orderRepository;
    private OutboxEventPublisher outboxEventPublisher;

    @Autowired
    public OrderService(OrderRepository orderRepository,OutboxEventPublisher outboxEventPublisher){
        this.orderRepository=orderRepository;
        this.outboxEventPublisher=outboxEventPublisher;
    }

    @Transactional
    public Order createOrder(Order order){
        order.setCreatedOn(new Date());
        orderRepository.save(order);
        outboxEventPublisher.fire(order.createOrderEvent());
        return order;
    }
}
