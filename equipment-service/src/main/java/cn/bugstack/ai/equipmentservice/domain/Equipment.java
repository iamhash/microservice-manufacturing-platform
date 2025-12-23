package cn.bugstack.ai.equipmentservice.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Equipment {
    private Long id;
    private String type;
    private String status;
    private BigDecimal oee;
    private LocalDateTime updateTime;
}
