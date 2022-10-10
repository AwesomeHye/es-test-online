package dev.elsboo.escore.dao;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.indices.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.StringReader;

/**
 * ElasticSearch java api client
 * ES 7.17 ~
 */
@Repository
@Slf4j
public class EsDao {
    private final ElasticsearchClient client;
    private final ObjectMapper objectMapper;

    public EsDao(ElasticsearchClient client, @Qualifier("root-wrap") ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }

    public static void print() {
        log.info("PRINT");
    }

    public boolean existIndex(String indexName) throws IOException {
        return client.indices().exists(builder -> builder.index(indexName)).value();
    }

    public DeleteIndexResponse deleteIndex(String indexName) throws IOException {
        return client.indices().delete(new DeleteIndexRequest.Builder().index(indexName).build());
    }

    /**
     * 인덱스 생성 - settings 포함
     * @param indexName
     * @param settings
     * @return
     * @throws IOException
     */
    public CreateIndexResponse createIndex(String indexName, String settings) throws IOException {
        log.info(settings);

        CreateIndexRequest createIndexRequest = new CreateIndexRequest.Builder()
                .index(indexName)
                .settings(builder -> builder.withJson(new StringReader(settings)))
                .build();
        return client.indices().create(createIndexRequest);
    }


    public CreateIndexResponse createIndex(String indexName, Object settingsObj) throws IOException {
        String settings = objectMapper.writeValueAsString(settingsObj);
        return createIndex(indexName, settings);
    }

    public AnalyzeResponse analyze(String indexName, String analyzerName, String inputText) throws IOException {
        AnalyzeRequest analyzeRequest = new AnalyzeRequest.Builder()
                .index(indexName)
                .analyzer(analyzerName)
                .text(inputText)
                .build();
        return client.indices().analyze(analyzeRequest);
    }


}
