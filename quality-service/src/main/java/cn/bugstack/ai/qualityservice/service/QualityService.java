package cn.bugstack.ai.qualityservice.service;

import cn.bugstack.ai.qualityservice.domain.QualityRecord;
import org.springframework.stereotype.Service;

@Service
public interface QualityService {
    //新增质检记录
    int insert(QualityRecord qualityRecord);
    //反馈质检问题
    void feedback(Long orderId, String issue);
}
