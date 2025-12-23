package cn.bugstack.ai.equipmentservice.controller;

import cn.bugstack.ai.equipmentservice.domain.Equipment;
import cn.bugstack.ai.equipmentservice.dto.EquipmentAvailabilityRequest;
import cn.bugstack.ai.equipmentservice.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @GetMapping("/{id}/status")
    public Equipment getStatus(@PathVariable Long id) {
        return equipmentService.getStatus(id);
    }
    @PostMapping("/availability")
    public Map<String, Object> checkAvailability(
            @RequestBody EquipmentAvailabilityRequest request) {

        boolean available = equipmentService.checkAvailability(
                request.getEquipmentIds()
        );

        Map<String, Object> result = new HashMap<>();
        result.put("available", available);
        return result;
    }
}
