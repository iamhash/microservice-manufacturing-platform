package cn.bugstack.ai.orderservice.service;

import cn.bugstack.ai.orderservice.domain.OrderInfo;
import cn.bugstack.ai.orderservice.dto.CreateOrderRequest;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    // 创建订单
    int createOrder(CreateOrderRequest request);

    // 查询订单
    OrderInfo queryOrderById(Long id);
    /**
     * 上报订单质量问题
     */
    void reportQualityIssue(Long orderId, String issue);

    }
