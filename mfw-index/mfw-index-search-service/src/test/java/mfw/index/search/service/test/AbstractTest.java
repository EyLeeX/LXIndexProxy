package mfw.index.search.service.test;


import mtime.lark.util.test.TestBase;
import mtime.lark.util.test.spring.SpringJUnit;
import mfw.index.search.service.SearchServiceBootstrap;

import org.junit.BeforeClass;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

public abstract class AbstractTest extends TestBase {

    @BeforeClass
    public static void init() {
        SpringJUnit.boot(Dummy.class, SearchServiceBootstrap.class);
        
    }

    /**
     * HACK: LOOP ENDLESS IF ANNOTATE ON AbstractTest DIRECTLY
     */
    @SpringBootApplication(exclude = { MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
    public static class Dummy {
    }
}
