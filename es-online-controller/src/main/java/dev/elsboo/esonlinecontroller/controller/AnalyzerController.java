package dev.elsboo.esonlinecontroller.controller;

import dev.elsboo.esonlinecontroller.Service.NoriAnalyzerService;
import dev.elsboo.esonlinecontroller.request.NoriAnalyzerRequestVo;
import dev.elsboo.esonlinecontroller.response.AnalyzedTextInfo;
import dev.elsboo.esonlinecontroller.response.AnalyzerResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("analyze")
@RequiredArgsConstructor
@Slf4j
public class AnalyzerController {
    private final NoriAnalyzerService noriAnalyzerService;

    @GetMapping("nori")
    public ResponseEntity<AnalyzerResponseVo> getNoriAnalyze(@ModelAttribute NoriAnalyzerRequestVo noriAnalyzerRequestVo) {
        AnalyzerResponseVo analyzerResponseVo = new AnalyzerResponseVo();


//        analyzerResponseVo = noriAnalyzerService.getAnalyzeInfo(noriAnalyzerRequestVo);

        analyzerResponseVo.setAnalyzedTextInfoList(List.of(new AnalyzedTextInfo(noriAnalyzerRequestVo.getInputText(), 0, 3)));
        return ResponseEntity.ok(analyzerResponseVo);
    }
}
