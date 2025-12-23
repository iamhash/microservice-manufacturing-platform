package cn.bugstack.ai.productionplanningservice.service.Impl;

import cn.bugstack.ai.api.Equipment.EquipmentDubboService;
import cn.bugstack.ai.api.Inventory.InventoryDubboService;
import cn.bugstack.ai.api.Inventory.dto.InventoryMaterial;
import cn.bugstack.ai.productionplanningservice.domain.ProductionPlan;
import cn.bugstack.ai.productionplanningservice.dto.CreatePlanRequest;
import cn.bugstack.ai.productionplanningservice.mapper.ProductionPlanMapper;
import cn.bugstack.ai.productionplanningservice.service.ProductionPlanService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductionPlanServiceImpl implements ProductionPlanService {
    @Autowired
    private ProductionPlanMapper productionPlanMapper;
    @DubboReference
    private InventoryDubboService inventoryService;
    @DubboReference
    private EquipmentDubboService equipmentService;


    @Override
    public int insert(CreatePlanRequest req) {
        ProductionPlan productionPlan = new ProductionPlan();
        productionPlan.setOrderId(req.getOrderId());
        productionPlan.setModelCode(req.getModelCode());
        productionPlan.setQuantity(req.getQuantity());
        productionPlan.setStatus("PLANNED");
        productionPlan.setCreateTime(LocalDateTime.now());
        return productionPlanMapper.insert(productionPlan);

    }

    @Override
    public ProductionPlan selectById(Long id) {
        return productionPlanMapper.selectById(id);
    }

    @Override
    public boolean checkMaterial(Long planId) {
        ProductionPlan productionPlan = productionPlanMapper.selectById(planId);
        return productionPlan!=null && inventoryService.checkMaterial(productionPlan.getModelCode(), productionPlan.getQuantity());


    }

    @Override
    public boolean checkEquipment(List<Long> equipmentIds) {
        return equipmentService.checkAvailability(equipmentIds);
    }
}
