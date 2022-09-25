package dev.elsboo.elasticsearchcore.dao;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.ExistsRequest;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class EsDaoTest {
    @Autowired
    ElasticsearchClient client;

    @Test
    @DisplayName("인덱스 생성")
    public void createIndex() throws IOException {
        log.info(client.cat().health().toString());
        Assertions.assertTrue(client.indices().exists(builder -> builder.index("test")).value());
    }
}