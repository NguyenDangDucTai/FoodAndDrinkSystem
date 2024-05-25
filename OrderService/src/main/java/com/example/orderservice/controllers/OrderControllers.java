/*
 * @ (#) OrderControllers.java     1.0     5/22/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved
 */
package com.example.orderservice.controllers;

import com.example.orderservice.models.Order;
import com.example.orderservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * @description:
 * @author: Huynh Minh Thu
 * @date: 2:46 AM 5/22/2024
 * @version: 1.0
 */
@RestController
@RequestMapping("/api")
public class OrderControllers {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    @Cacheable(value = "orders")
    public List<Order> getListOrder(){
        return orderService.getListOrder();
    }

    @GetMapping("/orders/{id}")
    @Cacheable(value = "order", key = "#id")
    public Order getUserById(@PathVariable(value = "id") long id){
        return orderService.getOrderById(id);
    }

    @PostMapping("/orders")
    @Cacheable(value = "order", key = "#result.id")
    public Order addOrder(@RequestBody Order order){
        return orderService.addOrder(order);
    }

    @DeleteMapping("/orders/{orderId}")
    @Cacheable(value = "order")
    public void deleteOrder(@PathVariable(value = "orderId") long orderId){
        orderService.deleteOrderById(orderId);
    }






}
