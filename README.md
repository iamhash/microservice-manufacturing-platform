# microservice-manufacturing-platform
## 项目简介
本项目是一个基于 Spring Cloud 生态构建的微服务管理平台示例，围绕订单处理、生产调度、资源管理与系统可观测性等核心场景，设计并实现了一套完整的分布式系统架构。项目采用微服务拆分模式，各服务独立部署、独立数据源，并通过服务注册发现、API 网关、熔断限流与链路追踪等机制，保障系统的高可用性与可扩展性。  系统重点关注微服务之间的协作关系与工程实践能力，包括服务治理、动态路由、容错降级以及全链路调用追踪，适用于需要复杂业务编排和高并发处理能力的后台服务平台。
## 系统模块

- **订单服务（Order Service）**  
  提供订单创建、变更与查询能力，支持订单优先级处理，并作为业务流程的核心入口。

- **生产计划服务（Production Planning Service）**  
  根据订单信息进行业务调度与资源协调，负责编排跨服务的业务流程。

- **设备监控服务（Equipment Monitoring Service）**  
  提供资源状态查询能力，用于模拟设备可用性检查与异常场景。

- **库存服务（Inventory Service）**  
  提供物料与资源可用性查询接口，支持生产计划的前置校验。

- **质量服务（Quality Service）**  
  记录业务质量信息，并支持跨服务的问题回溯与反馈。

## 项目系统架构图
<img width="2299" height="1019" alt="image" src="https://github.com/user-attachments/assets/82746210-1539-4a8b-9719-6e38e2996532" />

## 系统架构与工程特性

- **微服务架构设计**
  - 按业务域拆分服务
  - 每个服务独立数据库
  - 服务之间通过 REST API 通信

- **服务注册与发现**
  - 使用注册中心实现服务自动注册
  - 支持多实例部署与客户端负载均衡

- **统一 API 网关**
  - 统一入口与动态路由
  - 实现跨服务请求转发与解耦

- **服务熔断与容错**
  - 集成 Sentinel 实现限流与熔断
  - 支持服务降级与异常隔离

- **分布式链路追踪**
  - 集成 SkyWalking
  - 支持跨服务调用链路与拓扑分析

## 数据库表设计
以下是各个服务中所涉及的数据库表：
（1）订单管理服务（order_db）
order_info（订单表）：
字段	说明
id（PK）	订单id 
order_no	订单编号
model_code	车型编码
quantity	数量
priority	优先级
status	订单状态（已接收/排产中/生产中/完成）
create_time	创建时间

order_change_log（订单变更记录）:
字段	说明
id（PK）	主键 
order_id	订单id
change_type	变更类型（修改数量/变更车型/取消）
change_time	变更时间

（2）生产计划服务（planning_db）
production_plan（生产计划 MPS）
字段	说明
id（PK）	主键 
order_id	来源订单id
plan_date	计划日期
model_code	车型
quantity	数量
status	状态（待生产/生产中/完成）

material_requirement（物料需求计划 MRP）
字段	说明
id（PK）	主键 
plan_id	来源订单id
material_code	计划日期
required_qty	车型
available_qty	数量

（3）设备监控服务（equipment_db）
equipment_info（设备信息）
字段	说明
id（PK）	设备ID
type	类型（冲压/焊装/涂装/总装）
name	设备名称
status	当前状态（运行/停机/报警）

equipment_data（设备实时数据）
字段	说明
id（PK）	主键
equipment_id	设备ID
oee	设备综合效率
timestamp	采集时间

（4） 库存管理服务（inventory_db）
material_stock（原材料库存）
字段	说明
id（PK）	主键
material_code	原材料编码
stock_qty	库存数量

finished_vehicle（成品车库存）
字段	说明
id（PK）	设备ID
vin	VIN码
model_code	车型
status	整车状态（库存/调拨/出库）

（5） 质量管理服务（quality_db）
quality_record（质量记录）
字段	说明
id（PK）	主键 
stage	环节（冲压/焊接/涂装/总装/路试）
item_code	零件或整车编码
result	是否合格
record_time	时间

quality_feedback（质量问题反馈）
字段	说明
id（PK）	主键 
order_id	关联订单ID
issue	问题描述
feedback_time	时间


##API文档
根据数据库表可以对每个模块设计出对应的api如下（并满足服务间调用规则）：
（1）订单管理服务：(/api/orders)
1. 创建订单 ：POST /api/orders
Request：
{
  "orderNo": "ORD2025001",
  "modelCode": "SUV-X",
  "quantity": 20,
  "priority": 1
}
Response:
{
  "id": 1,
  "status": "CREATED"
}
2. 查询订单: GET /api/orders/{id}
Response:
{
  "id": 1,
  "orderNo": "ORD2025001",
  "modelCode": "SUV-X",
  "quantity": 20,
  "priority": 1,
  "status": "CREATED"
}
3. 获取订单变更记录：GET /api/orders/{id}/changes
4. 通知排产（调用生产计划服务）： POST /api/orders/{id}/trigger-plan
Response:
{"msg": "plan triggered"}
(2) 生产计划服务(/api/plans)
1. 生成生产计划（来自订单服务调用）: POST /api/plans
Request:
{
  "orderId": 1,
  "modelCode": "SUV-X",
  "quantity": 20
}
Response:
{
  "planId": 101,
  "status": "PLANNED"
}
2. 查询生产计划: GET /api/plans/{planId}
3. 物料可用性检查（调用库存服务）: GET /api/plans/{planId}/check-material
Response:
{
  "planId": 101,
  "materialReady": true
}
4.设备可用性检查（调用设备监控服务）: GET /api/plans/{planId}/check-equipment

(3) 设备监控服务(/api/equipment)
1.查询设备状态: GET /api/equipment/{id}/status
Response:
{
  "id": 10,
  "type": "WELD",
  "status": "RUNNING",
  "oee": 0.89
}
2.批量查询设备可用性（给计划服务）POST /api/equipment/availability
Request:
{
  "equipmentIds": [10, 11, 12]
}
Response:
{
  "available": true
}
(4)库存管理服务(/api/inventory)
1. 查询物料库存: GET /api/inventory/material/{materialCode}
Response:
{
  "materialCode": "ST-001",
  "stockQty": 1000
}
2.扣减物料（由生产计划触发）: POST /api/inventory/material/deduction
Request:
{
  "materialCode": "ST-001",
  "qty": 200
}
(5) 质量管理服务(/api/quality)
1. 上报质量记录: POST /api/quality/record
Request:
{
  "stage": "WELD",
  "itemCode": "WB10001",
  "result": "FAIL"
}
2. 向订单服务反馈质量问题: POST /api/quality/feedback
Request:
{
  "orderId": 1,
  "issue": "Weld defect on frame"
}
Response:
{"msg": "feedback accepted"}

