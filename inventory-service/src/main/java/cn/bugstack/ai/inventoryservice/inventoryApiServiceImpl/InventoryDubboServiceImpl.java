package cn.bugstack.ai.inventoryservice.inventoryApiServiceImpl;

import cn.bugstack.ai.api.Inventory.InventoryDubboService;
import cn.bugstack.ai.inventoryservice.domain.InventoryMaterial;
import cn.bugstack.ai.inventoryservice.service.InventoryService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@DubboService
@Component
public class InventoryDubboServiceImpl implements InventoryDubboService {
    @Autowired
    private InventoryService inventoryService;
    @Override
    public boolean checkMaterial(String materialCode, Integer qty) {
        InventoryMaterial inventoryMaterial = inventoryService.getInventoryMaterial(materialCode);
        return inventoryMaterial!=null && inventoryMaterial.getStockQty() >= qty;
    }
}
