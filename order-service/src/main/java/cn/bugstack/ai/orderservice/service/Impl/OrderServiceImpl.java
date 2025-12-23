package cn.bugstack.ai.orderservice.service.Impl;

import cn.bugstack.ai.api.Plan.ProductionPlanDubboService;
import cn.bugstack.ai.api.Plan.dto.CreatePlanRequest;
import cn.bugstack.ai.orderservice.domain.OrderInfo;
import cn.bugstack.ai.orderservice.dto.CreateOrderRequest;
import cn.bugstack.ai.orderservice.mapper.OrderMapper;
import cn.bugstack.ai.orderservice.service.OrderService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @DubboReference
    private ProductionPlanDubboService productionPlanService;

    @Override
    public int createOrder(CreateOrderRequest request) {
        OrderInfo order=new OrderInfo();
        order.setOrderNo(request.getOrderNo());
        order.setModelCode(request.getModelCode());
        order.setQuantity(request.getQuantity());
        order.setPriority(request.getPriority());
        order.setStatus("CREATED");
        order.setCreateTime(LocalDateTime.now());
        orderMapper.insert(order);

        CreatePlanRequest req=new CreatePlanRequest();
        req.setOrderId(order.getId());
        req.setModelCode(order.getModelCode());
        req.setQuantity(order.getQuantity());
        try {
            return productionPlanService.insert(req);
        }catch (Exception e){
            return 500-500-500-500-500;
        }


    }

    @Override
    public OrderInfo queryOrderById(Long id) {
        return orderMapper.selectById(id);
    }

    @Override
    public void reportQualityIssue(Long orderId, String issue) {
        System.out.println("订单服务被调用");

    }
}
