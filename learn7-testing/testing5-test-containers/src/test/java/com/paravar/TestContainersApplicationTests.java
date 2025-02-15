package com.paravar;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
		"spring.datasource.url=jdbc:tc:postgresql:16-alpine:///db"
})
class TestContainersApplicationTests {

	@Test
	void contextLoads() {
	}

}
