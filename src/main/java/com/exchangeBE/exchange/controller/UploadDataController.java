package com.exchangeBE.exchange.controller;

import com.exchangeBE.exchange.DynamicResponseBuilder;
import com.exchangeBE.exchange.dto.OptionDto;
import com.exchangeBE.exchange.dto.StageDto;
import com.exchangeBE.exchange.entity.Report.Options;
import com.exchangeBE.exchange.entity.Report.Stage;
import com.exchangeBE.exchange.service.UploadDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UploadDataController {

    private final UploadDataService uploadDataService;

    public UploadDataController(UploadDataService uploadDataService) {
        this.uploadDataService = uploadDataService;
    }

    @PostMapping("/upload/stage")
    public ResponseEntity<?> createStage(@RequestBody StageDto stageDto) {

        Stage stage = uploadDataService.createStage(stageDto);

        Map<String, Object> response = DynamicResponseBuilder.buildResponse(
                "reportType", stage.getReport().getType(),
                "stageId", stage.getId(),
                "stageNumber", stage.getStageNumber(),
                "content", stage.getContent()
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/upload/option")
    public ResponseEntity<?> createOption(@RequestBody OptionDto optionDto) {
        Options option = uploadDataService.createOption(optionDto);
        Map<String, Object> response = DynamicResponseBuilder.buildResponse(
                "stageId", option.getStage().getId(),
                "optionId", option.getId(),
                "content", option.getContent()
        );

        return ResponseEntity.ok(response);
    }

}
