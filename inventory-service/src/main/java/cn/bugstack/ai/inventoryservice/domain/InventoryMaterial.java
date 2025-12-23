package cn.bugstack.ai.inventoryservice.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InventoryMaterial {
    private Long id;
    private String materialCode;
    private Integer stockQty;
    private LocalDateTime updateTime;
}
