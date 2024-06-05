package com.example.springframework32730;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.slf4j.LoggerFactory.getLogger;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringFramework32730ApplicationTests {
    private static final Logger LOG = getLogger(SpringFramework32730ApplicationTests.class);

    @Autowired
    private TestRestTemplate template;

    @Test
    void testGet() {
        int i = 0;
        while (!Thread.interrupted()) {
            final ResponseEntity<String> response = template.getForEntity("/test", String.class);
            if (i % 1000 == 0) {
                LOG.info("response={}", response);
            }
            i++;
        }
    }
}

