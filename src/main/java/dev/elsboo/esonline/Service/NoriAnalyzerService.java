package dev.elsboo.esonline.Service;

import dev.elsboo.esonline.request.NoriAnalyzerRequestVo;
import dev.elsboo.esonline.response.AnalyzerResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NoriAnalyzerService {
    public AnalyzerResponseVo getAnalyzeInfo(NoriAnalyzerRequestVo noriAnalyzerRequestVo) {
        // index create
        createIndex(noriAnalyzerRequestVo);

        // analyze
        return analyze(noriAnalyzerRequestVo);
    }

    private void createIndex(NoriAnalyzerRequestVo noriAnalyzerRequestVo) {

    }

    private AnalyzerResponseVo analyze(NoriAnalyzerRequestVo noriAnalyzerRequestVo) {
        return null;
    }

}
