package cn.bugstack.ai.equipmentservice.service;

import cn.bugstack.ai.equipmentservice.domain.Equipment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EquipmentService {
    // 查询设备状态
    Equipment getStatus(Long id);

    boolean checkAvailability(List<Long> equipmentIds);
}
