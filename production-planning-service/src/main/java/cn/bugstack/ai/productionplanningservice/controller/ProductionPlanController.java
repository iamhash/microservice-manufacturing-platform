package cn.bugstack.ai.productionplanningservice.controller;

import cn.bugstack.ai.productionplanningservice.domain.ProductionPlan;
import cn.bugstack.ai.productionplanningservice.dto.CreatePlanRequest;
import cn.bugstack.ai.productionplanningservice.service.ProductionPlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/plans")
@Slf4j
public class ProductionPlanController {
    @Autowired
    private ProductionPlanService productionPlanService;
    @Value("${dubbo.protocol.port}")
    private String dubboPort;
    //新增生产计划
    @PostMapping
    public int createPlan(@RequestBody CreatePlanRequest request) {
        log.info("当前实例端口：{}", dubboPort);
        return productionPlanService.insert(request);
    }
    //查询计划
    @GetMapping("/{id}")
    public ProductionPlan queryPlanById(@PathVariable Long id) {
        return productionPlanService.selectById(id);
    }
    // 检查物料
    @GetMapping("/{planId}/check-material")
    public boolean checkMaterial(@PathVariable Long planId) {
        return productionPlanService.checkMaterial(planId);
    }
    // 检查设备
    @GetMapping("/check-equipment")
    public boolean checkEquipment( List<Long> equipmentIds) {
        return productionPlanService.checkEquipment(equipmentIds);
    }

}
