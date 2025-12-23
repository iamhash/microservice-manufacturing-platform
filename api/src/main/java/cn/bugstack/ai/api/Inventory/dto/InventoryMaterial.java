package cn.bugstack.ai.api.Inventory.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InventoryMaterial implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;
    private String materialCode;
    private Integer stockQty;
    private LocalDateTime updateTime;
}
