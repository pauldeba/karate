package com.standard.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public abstract class ApiTestBase {

    protected static Log log = LogFactory.getLog(ApiTestBase.class);

    public abstract void runAPITests();

    @BeforeAll
    public static void beforeAll() {
        // Could start a published Docker container before tests run
        log.info("beforeAll() was called.");
    }

    @BeforeEach
    public void beforeEach() {
        // TBD
        log.info("beforeEach() was called.");
    }

}
