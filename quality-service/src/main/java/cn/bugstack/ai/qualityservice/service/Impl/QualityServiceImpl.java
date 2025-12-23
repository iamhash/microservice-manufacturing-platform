package cn.bugstack.ai.qualityservice.service.Impl;

import cn.bugstack.ai.api.Order.OrderDubboService;
import cn.bugstack.ai.qualityservice.domain.QualityRecord;
import cn.bugstack.ai.qualityservice.mapper.QualityRecordMapper;
import cn.bugstack.ai.qualityservice.service.QualityService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class QualityServiceImpl implements QualityService {

    @Autowired
    private QualityRecordMapper qualityRecordMapper;
    @DubboReference
    private OrderDubboService orderService;
    @Override
    public int insert(QualityRecord qualityRecord) {
        qualityRecord.setCreateTime(LocalDateTime.now());
        return qualityRecordMapper.insert(qualityRecord);
    }

    @Override
    public void feedback(Long orderId, String issue) {
         orderService.reportQualityIssue(orderId, issue);
    }
}
