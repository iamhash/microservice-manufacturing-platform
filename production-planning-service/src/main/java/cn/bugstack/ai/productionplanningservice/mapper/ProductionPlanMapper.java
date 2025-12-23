package cn.bugstack.ai.productionplanningservice.mapper;

import cn.bugstack.ai.productionplanningservice.domain.ProductionPlan;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductionPlanMapper {
    // 创建生产计划
    int insert(ProductionPlan productionPlan);

    // 查询生产计划
    ProductionPlan selectById(Long id);
}
