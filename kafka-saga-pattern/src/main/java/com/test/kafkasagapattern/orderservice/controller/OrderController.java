package com.test.kafkasagapattern.orderservice.controller;

import com.test.kafkasagapattern.model.dto.OrderRequestDTO;
import com.test.kafkasagapattern.model.dto.OrderResponseDTO;
import com.test.kafkasagapattern.orderservice.entity.PurchaseOrder;
import com.test.kafkasagapattern.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public PurchaseOrder createOrder(@RequestBody OrderRequestDTO dto){
        return this.orderService.createOrder(dto);
    }

    @GetMapping("/all")
    public List<OrderResponseDTO> getOrders(){
        return this.orderService.getAll();
    }

}
