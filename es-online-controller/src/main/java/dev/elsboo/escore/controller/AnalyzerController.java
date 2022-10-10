package dev.elsboo.escore.controller;

import dev.elsboo.escore.Service.NoriAnalyzerService;
import dev.elsboo.escore.request.NoriAnalyzerRequestVo;
import dev.elsboo.escore.response.AnalyzedTextInfo;
import dev.elsboo.escore.response.AnalyzerResponseVo;
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
    public ResponseEntity<AnalyzerResponseVo> getNoriAnalyze(@ModelAttribute NoriAnalyzerRequestVo noriAnalyzerRequestVo) throws Exception {
        AnalyzerResponseVo analyzerResponseVo = new AnalyzerResponseVo();

        analyzerResponseVo = noriAnalyzerService.getAnalyzeInfo(noriAnalyzerRequestVo);

        return ResponseEntity.ok(analyzerResponseVo);
    }
}
