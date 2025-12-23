package cn.bugstack.ai.equipmentservice.mapper;

import cn.bugstack.ai.equipmentservice.domain.Equipment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EquipmentMapper {
    // 查询设备
    Equipment selectById(Long id);

    // 批量查询设备
    List<Equipment> selectByIds(List<Long> ids);
}
