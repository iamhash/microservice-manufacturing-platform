package cn.bugstack.ai.orderservice.mapper;

import cn.bugstack.ai.orderservice.domain.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

    // 创建订单
    public int insert(OrderInfo orderInfo);

    // 查询订单
    public OrderInfo selectById(Long id);


}
