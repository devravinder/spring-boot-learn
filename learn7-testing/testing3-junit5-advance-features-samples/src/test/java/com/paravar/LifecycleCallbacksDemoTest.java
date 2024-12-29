package com.paravar;

import org.junit.jupiter.api.*;

class LifecycleCallbacksDemoTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("--------beforeAll()---------");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("--------afterAll()---------");
    }

    @BeforeEach
    void setUp() {
        System.out.println("--------setUp()---------");
    }

    @AfterEach
    void tearDown() {
        System.out.println("--------tearDown()---------");
    }

    @Test
    void test1() {
        System.out.println("------------test1------------");
    }

    @Test
    void test2() {
        System.out.println("------------test2------------");
    }
}
