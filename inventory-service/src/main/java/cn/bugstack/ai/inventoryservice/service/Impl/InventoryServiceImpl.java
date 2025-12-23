package cn.bugstack.ai.inventoryservice.service.Impl;

import cn.bugstack.ai.inventoryservice.domain.InventoryMaterial;
import cn.bugstack.ai.inventoryservice.mapper.InventoryMapper;
import cn.bugstack.ai.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryMapper inventoryMapper;
    @Override
    public InventoryMaterial getInventoryMaterial(String materialCode) {
        return inventoryMapper.selectByMaterialCode(materialCode);
    }

    @Transactional
    public int deduct(String materialCode, Integer quantity) {
        int updated=inventoryMapper.deductStock(materialCode,quantity);
        if(updated==0){
            throw new RuntimeException("库存不足");
        }
        return 0;
    }
}
