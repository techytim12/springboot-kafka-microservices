package com.techm.springboot.orderservice.controller;

import com.techm.springboot.basedomains.constant.OrderStatus;
import com.techm.springboot.basedomains.dto.Order;
import com.techm.springboot.basedomains.dto.OrderEvent;
import com.techm.springboot.orderservice.kafka.OrderProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping("/orders")
    public String placeOrder(@RequestBody Order order){

        order.setOrderId(UUID.randomUUID().toString());

        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus(String.valueOf(OrderStatus.PENDING));
        orderEvent.setMessage("order status is in pending");
        orderEvent.setOrder(order);

        orderProducer.sendMessage(orderEvent);

        return "Order placed Successfully";
    }
}
