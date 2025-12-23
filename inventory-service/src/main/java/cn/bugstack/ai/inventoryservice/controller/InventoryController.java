package cn.bugstack.ai.inventoryservice.controller;

import cn.bugstack.ai.inventoryservice.domain.InventoryMaterial;
import cn.bugstack.ai.inventoryservice.dto.InventoryDeductionRequest;
import cn.bugstack.ai.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/inventory/material")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/{materialCode}")
    public Map<String,Object> get(@PathVariable String materialCode){
        InventoryMaterial inventoryMaterial = inventoryService.getInventoryMaterial(materialCode);
        Map<String,Object> result = new HashMap<>();
        result.put("status", "OK");
//        result.put("materialCode", inventoryMaterial.getMaterialCode());
//        result.put("stockQty", inventoryMaterial.getStockQty());
        return result;
    }

    @PostMapping("/deduction")
    public String deduct(@RequestBody InventoryDeductionRequest request){
        int deduct = inventoryService.deduct(request.getMaterialCode(), request.getQuantity());
        if(deduct>0){
            return "SUCCESS";
        }
        return "FAIL";
    }
}
