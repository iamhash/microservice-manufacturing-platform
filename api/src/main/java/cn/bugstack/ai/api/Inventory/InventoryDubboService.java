package cn.bugstack.ai.api.Inventory;

public interface InventoryDubboService {
    /**
     * 检查物料是否充足
     */
    boolean checkMaterial(String materialCode, Integer qty);
}
