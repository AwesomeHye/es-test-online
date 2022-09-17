package dev.hyein.norionline.controller;

import dev.hyein.norionline.request.NoriAnalyzerRequestVo;
import dev.hyein.norionline.response.AnalyzedTextInfo;
import dev.hyein.norionline.response.AnalyzerResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("analyze")
@Slf4j
public class AnalyzerController {

    @GetMapping("nori")
    public ResponseEntity<AnalyzerResponseVo> getNoriAnalyze(@ModelAttribute NoriAnalyzerRequestVo noriAnalyzerRequestVo) {
        AnalyzerResponseVo analyzerResponseVo = new AnalyzerResponseVo();
        // index create

        // indexing

        analyzerResponseVo.setAnalyzedTextInfoList(List.of(new AnalyzedTextInfo(noriAnalyzerRequestVo.getInputText(), 0, 3)));
        return ResponseEntity.ok(analyzerResponseVo);
    }
}
