package cn.bugstack.ai.qualityservice.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QualityRecord {
    private Long id;
    private String stage;
    private String itemCode;
    private String result;
    private LocalDateTime createTime;
}
