package dev.elsboo.elasticsearchcore.dao;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.mapping.TypeMapping;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.IndexSettings;
import co.elastic.clients.json.JsonpMapper;
import com.fasterxml.jackson.core.json.JsonReadContext;
import jakarta.json.Json;
import jakarta.json.JsonReader;
import jakarta.json.JsonReaderFactory;
import jakarta.json.stream.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.indices.IndexTemplateMetadata;
import org.elasticsearch.cluster.metadata.IndexMetadata;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.xcontent.XContentBuilder;
import org.elasticsearch.xcontent.XContentFactory;
import org.elasticsearch.xcontent.XContentParser;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.StringReader;

@Repository
@RequiredArgsConstructor
@Slf4j
public class EsDao {
    private final ElasticsearchClient client;

    public static void print() {
        log.info("PRINT");
    }

    public boolean existIndex(String indexName) throws IOException {
        return client.indices().exists(builder -> builder.index(indexName)).value();
    }

    public void createIndex(String indexName, String settings) throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest.Builder()
                .index(indexName)
                .settings(builder -> builder.withJson(new StringReader(settings)))
                .build();
        client.indices().create(createIndexRequest);
    }



}
