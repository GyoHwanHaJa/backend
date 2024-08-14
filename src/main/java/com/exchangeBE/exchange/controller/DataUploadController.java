package com.exchangeBE.exchange.controller;

import com.exchangeBE.exchange.dto.DataUploadDto;
import com.exchangeBE.exchange.entity.Report.Stage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/upload")
public class DataUploadController {
    @PostMapping("/stage/life/{stageNumber}")
    public Stage createLifeStage(@PathVariable("stageNumber") String stageNumber, @RequestBody DataUploadDto dataUploadDto) {

    }
    @PostMapping("/stage/middle/{stageNumber}")
    public Stage createMiddleStage(@PathVariable("stageNumber") String stageNumber){

    }

    @PostMapping("/stage/final/{stageNumber}")
    public Stage createFinalStage(@PathVariable("stageNumber") String stageNumber){

    }

    @PostMapping("/option")
}
