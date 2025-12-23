package cn.bugstack.ai.equipmentservice.equipmentApiServiceImpl;

import cn.bugstack.ai.api.Equipment.EquipmentDubboService;
import cn.bugstack.ai.equipmentservice.service.EquipmentService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@DubboService
@Component
public class EquipmentDubboServiceImpl implements EquipmentDubboService {
    @Autowired
    private EquipmentService equipmentService;
    @Override
    public boolean checkAvailability(List<Long> equipmentIds) {
        return equipmentService.checkAvailability(equipmentIds);
    }
}
