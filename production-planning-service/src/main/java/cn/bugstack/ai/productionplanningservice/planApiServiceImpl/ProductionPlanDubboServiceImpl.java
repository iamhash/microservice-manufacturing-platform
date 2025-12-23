package cn.bugstack.ai.productionplanningservice.planApiServiceImpl;

import cn.bugstack.ai.api.Plan.ProductionPlanDubboService;
import cn.bugstack.ai.api.Plan.dto.CreatePlanRequest;
import cn.bugstack.ai.productionplanningservice.service.ProductionPlanService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@DubboService
@Component
public class ProductionPlanDubboServiceImpl implements ProductionPlanDubboService {
    @Autowired
    private ProductionPlanService productionPlanService;
    @Override
    public int insert(CreatePlanRequest req) {
        cn.bugstack.ai.productionplanningservice.dto.CreatePlanRequest request = new cn.bugstack.ai.productionplanningservice.dto.CreatePlanRequest();
        request.setOrderId(req.getOrderId());
        request.setModelCode(req.getModelCode());
        request.setQuantity(req.getQuantity());
        return productionPlanService.insert(request);
    }
}
