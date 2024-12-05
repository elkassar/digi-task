package com.digi.task;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AliasFor;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@EnableAutoConfiguration
@Configuration
@ComponentScan(basePackages = {"com.digi.task"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskApplicationTests {
	@Test
	void contextLoads() {
	}
}
