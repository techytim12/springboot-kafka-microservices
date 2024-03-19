package com.techm.springboot.basedomains.dto;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private String orderId;
    private String orderName;
    private int quantity;
    private double price;
}
