package cn.bugstack.ai.orderservice.controller;

import cn.bugstack.ai.orderservice.domain.OrderInfo;
import cn.bugstack.ai.orderservice.dto.CreateOrderRequest;
import cn.bugstack.ai.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // 创建订单
    @PostMapping
    public int createOrder(
            @RequestBody CreateOrderRequest request) {
        return orderService.createOrder(request);
    }

    // 查询订单
    @GetMapping("/{id}")
    public OrderInfo queryOrderById(
            @PathVariable Long id) {
        return orderService.queryOrderById(id);
    }
}
