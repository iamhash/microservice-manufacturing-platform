package cn.bugstack.ai.qualityservice.mapper;

import cn.bugstack.ai.qualityservice.domain.QualityRecord;

public interface QualityRecordMapper {
    //新增质检记录
    int insert(QualityRecord qualityRecord);
}
