package dev.hyein.norionline.controller;

import dev.hyein.norionline.request.NoriAnalyzerRequestVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("analyze")
@Slf4j
public class AnalyzerController {

    @GetMapping("nori")
    public ResponseEntity<NoriAnalyzerRequestVo> getNoriAnalyze(@ModelAttribute NoriAnalyzerRequestVo noriAnalyzerRequestVo) {
        log.info(noriAnalyzerRequestVo.getDecompoundMode());
        return ResponseEntity.ok(noriAnalyzerRequestVo);
    }
}
