package cn.bugstack.ai.inventoryservice.mapper;

import cn.bugstack.ai.inventoryservice.domain.InventoryMaterial;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface InventoryMapper {
    InventoryMaterial selectByMaterialCode(String materialCode);

    int deductStock(@Param("materialCode") String materialCode,
                    @Param("qty") Integer qty);
}
