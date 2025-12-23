package cn.bugstack.ai.orderservice.dto;

import lombok.Data;

@Data
public class CreateOrderRequest {

    private String orderNo;
    private String modelCode;
    private Integer quantity;
    private Integer priority;
}
