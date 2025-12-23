package cn.bugstack.ai.orderservice.orderApiServiceImpl;

import cn.bugstack.ai.api.Order.OrderDubboService;
import cn.bugstack.ai.orderservice.service.OrderService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@DubboService
@Component
public class OrderDubboServiceImpl implements OrderDubboService {
    @Autowired
    private OrderService orderService;
    @Override
    public void reportQualityIssue(Long orderId, String issue) {
        orderService.reportQualityIssue(orderId, issue);
    }
}
