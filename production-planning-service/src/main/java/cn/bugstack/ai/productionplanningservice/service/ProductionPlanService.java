package cn.bugstack.ai.productionplanningservice.service;

import cn.bugstack.ai.productionplanningservice.domain.ProductionPlan;
import cn.bugstack.ai.productionplanningservice.dto.CreatePlanRequest;
import cn.bugstack.ai.productionplanningservice.mapper.ProductionPlanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductionPlanService {
    //创建生产计划
    int insert(CreatePlanRequest req);

    //查询生产计划
    ProductionPlan selectById(Long id);


    boolean checkMaterial(Long planId);

    boolean checkEquipment(List<Long> equipmentIds);
}
