package cn.bugstack.ai.inventoryservice.dto;

import lombok.Data;

@Data
public class InventoryDeductionRequest {
    private String materialCode;
    private Integer quantity;
}
