package com.bringbackdada.site;

import com.bringbackdada.site.bootstrap.TestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DataLoader implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(DataLoader.class);

    @Override
    public void run(String... args) throws Exception {

        // ADD TEST DATA - DEV ONLY
        // TODO only for development - delete for production

        logger.info("DataLoader executed");
    }
}
