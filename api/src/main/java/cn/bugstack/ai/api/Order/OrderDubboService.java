package cn.bugstack.ai.api.Order;


public interface OrderDubboService {
    /**
     * 上报订单质量问题
     */
    void reportQualityIssue(Long orderId, String issue);
}
