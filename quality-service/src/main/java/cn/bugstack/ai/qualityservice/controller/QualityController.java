package cn.bugstack.ai.qualityservice.controller;

import cn.bugstack.ai.qualityservice.domain.QualityRecord;
import cn.bugstack.ai.qualityservice.dto.QualityFeedBackRequest;
import cn.bugstack.ai.qualityservice.dto.QualityRecordRequest;
import cn.bugstack.ai.qualityservice.service.QualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quality")
public class QualityController {
    @Autowired
    private QualityService qualityService;

    @PostMapping
    public int insert(@RequestBody QualityRecordRequest req) {
        QualityRecord qualityRecord = new QualityRecord();
        qualityRecord.setStage(req.getStage());
        qualityRecord.setItemCode(req.getItemCode());
        qualityRecord.setResult(req.getResult());
        return qualityService.insert(qualityRecord);
    }
    @PostMapping("/feedback")
    public void feedback(@RequestBody QualityFeedBackRequest req) {
         qualityService.feedback(req.getOrderId(), req.getIssue());
    }
}
