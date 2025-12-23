package cn.bugstack.ai.api.Equipment;

import java.util.List;

public interface EquipmentDubboService {
    // 检查设备可用性
    boolean checkAvailability(List<Long> equipmentIds);
}
