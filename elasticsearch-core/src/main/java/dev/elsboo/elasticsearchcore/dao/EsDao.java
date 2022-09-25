package dev.elsboo.elasticsearchcore.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class EsDao {
    public static void print() {
        log.info("PRINT");
    }

    public void createIndex() {

    }

}
