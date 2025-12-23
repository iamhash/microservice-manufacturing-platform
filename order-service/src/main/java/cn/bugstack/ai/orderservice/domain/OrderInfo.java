package cn.bugstack.ai.orderservice.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderInfo {

    private Long id;
    private String orderNo;
    private String modelCode;
    private Integer quantity;
    private Integer priority;
    private String status;
    private LocalDateTime createTime;
}
