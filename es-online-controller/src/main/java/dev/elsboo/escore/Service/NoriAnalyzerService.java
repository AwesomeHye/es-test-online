package dev.elsboo.escore.Service;

import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import dev.elsboo.escore.dao.EsDao;
import dev.elsboo.escore.index.Analysis;
import dev.elsboo.escore.request.NoriAnalyzerRequestVo;
import dev.elsboo.escore.response.AnalyzerResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoriAnalyzerService {
    private final EsDao esDao;

    public AnalyzerResponseVo getAnalyzeInfo(NoriAnalyzerRequestVo noriAnalyzerRequestVo) throws Exception {
        noriAnalyzerRequestVo.setIndexName(UUID.randomUUID().toString());
        // index create
        CreateIndexResponse createIndexResponse = createIndex(noriAnalyzerRequestVo);
        if(!createIndexResponse.acknowledged()) {
            throw new Exception("Create Index failed: " + createIndexResponse);
        }

        // analyze
        return analyze(noriAnalyzerRequestVo);
    }

    private CreateIndexResponse createIndex(NoriAnalyzerRequestVo noriAnalyzerRequestVo) throws IOException {
        Analysis analysis = convert2Analysis(noriAnalyzerRequestVo);
        log.info(analysis.toString());
        return esDao.createIndex(noriAnalyzerRequestVo.getIndexName(), analysis);
    }

    private Analysis convert2Analysis(NoriAnalyzerRequestVo noriAnalyzerRequestVo) {
        Analysis analysis = new Analysis();
        analysis.setTokenizer(createNoriTokenizer(noriAnalyzerRequestVo));
        analysis.setFilter(createNoriFilter(noriAnalyzerRequestVo));
        analysis.setAnalyzer(createNoriAnalyzer(noriAnalyzerRequestVo));
        return analysis;
    }

    private Map<String, Map<String, Object>> createNoriTokenizer(NoriAnalyzerRequestVo noriAnalyzerRequestVo) {
        Map<String, Map<String, Object>> noriTk = new HashMap<>();
        Map<String, Object> tk = new HashMap<>();
        tk.put("type", "nori_tokenizer");
        if(StringUtils.hasText(noriAnalyzerRequestVo.getDecompoundMode())) { // mode 값 validate
            tk.put("decompound_mode", noriAnalyzerRequestVo.getDecompoundMode());
        }

        tk.put("discard_punctuation", noriAnalyzerRequestVo.isDiscardPunctuation());

        if(noriAnalyzerRequestVo.getUserDictionaryRules().size() > 0) { // mode 값 validate
            tk.put("user_dictionary_rules", noriAnalyzerRequestVo.getUserDictionaryRules());
        }

        noriTk.put("nori_tk", tk);
        return noriTk;
    }

    private Map<String, Map<String, Object>> createNoriFilter(NoriAnalyzerRequestVo noriAnalyzerRequestVo) {
        Map<String, Map<String, Object>> filter = new HashMap<>();
        return filter;
    }

    private Map<String, Map<String, Object>> createNoriAnalyzer(NoriAnalyzerRequestVo noriAnalyzerRequestVo) {
        Map<String, Map<String, Object>> analyzer = new HashMap<>();

        Map<String, Object> az = new HashMap<>();
        az.put("type", "custom");
        az.put("tokenizer", "nori_tk");

        analyzer.put("nori_az", az);
        return analyzer;
    }

    private AnalyzerResponseVo analyze(NoriAnalyzerRequestVo noriAnalyzerRequestVo) {
        return null;
    }

}
