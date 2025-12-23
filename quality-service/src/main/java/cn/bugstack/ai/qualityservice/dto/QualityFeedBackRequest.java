package cn.bugstack.ai.qualityservice.dto;

import lombok.Data;

@Data
public class QualityFeedBackRequest {
    private Long orderId;
    private String issue;
}
