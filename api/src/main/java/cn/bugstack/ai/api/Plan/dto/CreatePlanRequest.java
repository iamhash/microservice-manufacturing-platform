package cn.bugstack.ai.api.Plan.dto;

import lombok.Data;

@Data
public class CreatePlanRequest implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    private Long orderId;
    private String modelCode;
    private Integer quantity;
}
