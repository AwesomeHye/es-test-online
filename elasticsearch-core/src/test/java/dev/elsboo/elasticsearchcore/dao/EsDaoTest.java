package dev.elsboo.elasticsearchcore.dao;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.ExistsRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.elsboo.elasticsearchcore.index.Analysis;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import jakarta.json.JsonReaderFactory;
import jakarta.json.stream.JsonGeneratorFactory;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.json.JSONObject;
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

    @Autowired
    EsDao esDao;


    @Test
    @DisplayName("인덱스 생성")
    public void createIndex() throws IOException {
        log.info(client.cat().health().toString());
        Assertions.assertTrue(esDao.existIndex("test"));
    }

    @Test
    @DisplayName("인덱스 settings string 생성")
    public void createSettingsIndex() throws IOException {
        esDao.createIndex("test1", setting());
        Assertions.assertTrue(esDao.existIndex("test1"));
    }

    public String setting() {
        return "{\n" +
                "    \"analysis\": {\n" +
                "      \"tokenizer\": {\n" +
                "        \"nori_tk\": {\n" +
                "          \"type\": \"nori_tokenizer\",\n" +
                "          \"decompound_mode\": \"mixed\",\n" +
                "          \"discard_punctuation\": \"false\",\n" +
                "          \"user_dictionary_rules\": [\"c++\", \"C샤프\", \"세종\", \"세종시 세종 시\"]\n" +
                "        }\n" +
                "      },\n" +
                "      \"filter\": {\n" +
                "        \"nori_pos_f\": {\n" +
                "          \"type\": \"nori_part_of_speech\",\n" +
                "          \"stoptags\": [\n" +
                "            \"NR\"\n" +
                "          ]\n" +
                "        }\n" +
                "      },\n" +
                "      \"analyzer\": {\n" +
                "        \"nori_anal\": {\n" +
                "          \"type\": \"custom\",\n" +
                "          \"tokenizer\": \"nori_tk\",\n" +
                "          \"filter\" : [\n" +
                "            \"nori_pos_f\"\n" +
                "          ]\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }";
    }

    @Test
    @DisplayName("vo settings test")
    public void json() throws IOException {
        Analysis analysis = new Analysis();
        esDao.createIndex("test2", analysis);
        Assertions.assertTrue(esDao.existIndex("test2"));
    }
}