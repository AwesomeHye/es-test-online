package dev.elsboo.esonlinecontroller;

import dev.elsboo.elasticsearchcore.dao.EsDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class EsOnlineControllerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsOnlineControllerApplication.class, args);
    }

    @PostConstruct
    public void d() {
        EsDao.print();
    }

}
