package dev.elsboo.elasticsearchcore.dao;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.mapping.TypeMapping;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.indices.IndexTemplateMetadata;
import org.elasticsearch.cluster.metadata.IndexMetadata;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.xcontent.XContentBuilder;
import org.elasticsearch.xcontent.XContentFactory;
import org.elasticsearch.xcontent.XContentParser;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
@RequiredArgsConstructor
@Slf4j
public class EsDao {
    private final ElasticsearchClient client;

    public static void print() {
        log.info("PRINT");
    }

    public void createIndex(String indexName) throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest.Builder()
//                    .settings(new IndexSettings(IndexMetadata.builder("dd").settings(IndexTemplateMetadata.Builder.fromXContent(XContentFactory.jsonBuilder().field("ff")).).build()))
                .build();
        client.indices().create(createIndexRequest);
    }

}
