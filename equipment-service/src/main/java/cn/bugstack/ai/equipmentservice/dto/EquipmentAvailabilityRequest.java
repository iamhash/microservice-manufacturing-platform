package cn.bugstack.ai.equipmentservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class EquipmentAvailabilityRequest {
    List<Long> equipmentIds;
}
