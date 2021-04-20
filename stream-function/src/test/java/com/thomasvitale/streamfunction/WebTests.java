package com.thomasvitale.streamfunction;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
class WebTests {

	@Autowired
	private WebTestClient webClient;

	@Test
	void testFunctionDefinitionApi() {
		webClient.post()
				.contentType(MediaType.TEXT_PLAIN)
				.bodyValue("Spring Cloud")
				.exchange()
				.expectBody(String.class)
				.isEqualTo("DUOLC GNIRPS");
	}
}
