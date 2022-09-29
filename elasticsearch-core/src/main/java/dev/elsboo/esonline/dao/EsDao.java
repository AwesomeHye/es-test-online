package dev.elsboo.esonline.dao;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.StringReader;

/**
 * ElasticSearch java api client
 * ES 7.17 ~
 */
@Repository
@RequiredArgsConstructor
@Slf4j
public class EsDao {
    private final ElasticsearchClient client;
    private final ObjectMapper objectMapper;

    public static void print() {
        log.info("PRINT");
    }

    public boolean existIndex(String indexName) throws IOException {
        return client.indices().exists(builder -> builder.index(indexName)).value();
    }

    public CreateIndexResponse createIndex(String indexName, String settings) throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest.Builder()
                .index(indexName)
                .settings(builder -> builder.withJson(new StringReader(settings)))
                .build();
        return client.indices().create(createIndexRequest);
    }

    public CreateIndexResponse createIndex(String indexName, Object settingsObj) throws IOException {
        String settings = objectMapper.writeValueAsString(settingsObj);

        CreateIndexRequest createIndexRequest = new CreateIndexRequest.Builder()
                .index(indexName)
                .settings(builder -> builder.withJson(new StringReader(settings)))
                .build();
        return client.indices().create(createIndexRequest);
    }


}
