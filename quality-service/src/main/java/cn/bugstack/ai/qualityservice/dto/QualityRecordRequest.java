package cn.bugstack.ai.qualityservice.dto;

import lombok.Data;

@Data
public class QualityRecordRequest {
    private String stage;
    private String itemCode;
    private String result;
}
