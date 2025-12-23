package cn.bugstack.ai.equipmentservice.service.Impl;

import cn.bugstack.ai.equipmentservice.domain.Equipment;
import cn.bugstack.ai.equipmentservice.mapper.EquipmentMapper;
import cn.bugstack.ai.equipmentservice.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    private EquipmentMapper equipmentMapper;

    @Override
    public Equipment getStatus(Long id) {
        return equipmentMapper.selectById(id);
    }

    @Override
    public boolean checkAvailability(List<Long> equipmentIds) {
        List<Equipment> list=equipmentMapper.selectByIds(equipmentIds);
        return list.stream().allMatch(e->"RUNNING".equals(e.getStatus()));
    }
}
