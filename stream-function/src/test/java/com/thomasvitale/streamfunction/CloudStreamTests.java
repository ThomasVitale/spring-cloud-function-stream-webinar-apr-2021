package com.thomasvitale.streamfunction;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.support.GenericMessage;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(TestChannelBinderConfiguration.class)
class CloudStreamTests {

	@Autowired
	@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
	private InputDestination input;

	@Autowired
	@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
	private OutputDestination output;

	@Test
	void testUppercaseThenReverse() {
		this.input.send(new GenericMessage<>("Spring Cloud".getBytes()));
		assertThat(output.receive().getPayload()).isEqualTo("DUOLC GNIRPS".getBytes());
	}
}
