package cn.bugstack.ai.productionplanningservice.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductionPlan {
    private Long id;
    private Long orderId;
    private String modelCode;
    private Integer quantity;
    private String status;
    private LocalDateTime createTime;
}
