package com.paravar;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

class ConditionalTestExecutionDemoTest {

    @Test
    @EnabledOnOs(OS.MAC)
    void shouldRunOnlyOnMacOS() {
        System.out.println("This is a test running on MacOS");
    }

    @Test
    @EnabledOnOs(OS.LINUX)
    void shouldRunOnlyOnLinux() {
        System.out.println("This is a test running on Linux");
    }

    @Test
    @EnabledOnJre(JRE.JAVA_23)
    void shouldRunOnlyOnJre23() {
        System.out.println("This is a test running on Java 23");
    }

    @Test
    @EnabledOnJre(JRE.JAVA_21)
    void shouldRunOnlyOnJre21() {
        System.out.println("This is a test running on Java 21");
    }

}
