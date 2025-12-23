package cn.bugstack.ai.api.Plan;

import cn.bugstack.ai.api.Plan.dto.CreatePlanRequest;

public interface ProductionPlanDubboService {
    //创建生产计划
    int insert(CreatePlanRequest req);
}
