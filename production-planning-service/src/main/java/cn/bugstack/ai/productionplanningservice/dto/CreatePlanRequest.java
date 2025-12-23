package cn.bugstack.ai.productionplanningservice.dto;

import lombok.Data;

@Data
public class CreatePlanRequest {
    private Long orderId;
    private String modelCode;
    private Integer quantity;
}
