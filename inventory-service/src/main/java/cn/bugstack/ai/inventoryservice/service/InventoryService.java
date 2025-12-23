package cn.bugstack.ai.inventoryservice.service;

import cn.bugstack.ai.inventoryservice.domain.InventoryMaterial;
import org.springframework.stereotype.Service;

@Service
public interface InventoryService {
    // 查询库存
    InventoryMaterial getInventoryMaterial(String materialCode);

    // 更新库存
    int deduct(String materialCode, Integer quantity);
}
