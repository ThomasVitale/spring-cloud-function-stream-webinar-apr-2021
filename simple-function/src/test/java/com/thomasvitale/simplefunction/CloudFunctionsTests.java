package com.thomasvitale.simplefunction;

import java.util.function.Function;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.cloud.function.context.test.FunctionalSpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@FunctionalSpringBootTest
class CloudFunctionsTests {

	@Autowired
	private FunctionCatalog catalog;

	@Test
	void testUppercase() {
		String input = "Spring Cloud";
		String expectedOutput = "SPRING CLOUD";

		Function<String, String> uppercaseFunction = catalog.lookup(Function.class, "uppercase");
		String actualOutput = uppercaseFunction.apply(input);

		assertThat(actualOutput).isEqualTo(expectedOutput);
	}

	@Test
	void testReverse() {
		String input = "Spring Cloud";
		String expectedOutput = "duolC gnirpS";

		Function<String, String> reverseFunction = catalog.lookup(Function.class, "reverse");
		String actualOutput = reverseFunction.apply(input);

		assertThat(actualOutput).isEqualTo(expectedOutput);
	}

	@Test
	@DisplayName("Function composition with same data type")
	void testUppercaseThenReverse() {
		String input = "Spring Cloud";
		String expectedOutput = "DUOLC GNIRPS";

		Function<String, String> upperThenReverse = catalog.lookup(Function.class, "uppercase|reverse");
		String actualOutput = upperThenReverse.apply(input);

		assertThat(actualOutput).isEqualTo(expectedOutput);
	}

	@Test
	@DisplayName("Function composition with different data type")
	void testUppercaseThenReverseWithMessage() {
		String input = "Spring Cloud";
		String expectedOutput = "DUOLC GNIRPS";

		Function<String, String> upperThenReverse = catalog.lookup(Function.class, "uppercase|reverseWithMessage");
		String actualOutput = upperThenReverse.apply(input);

		assertThat(actualOutput).isEqualTo(expectedOutput);
	}

	@Test
	@DisplayName("Longer function composition with different data type")
	void testUppercaseThenReverseWithMessageThenReverse() {
		String input = "Spring Cloud";
		String expectedOutput = "SPRING CLOUD";

		Function<String, String> upperThenReverse = catalog.lookup(Function.class, "uppercase|reverseWithMessage|reverse");
		String actualOutput = upperThenReverse.apply(input);

		assertThat(actualOutput).isEqualTo(expectedOutput);
	}

	@Test
	@DisplayName("Function composition with both imperative and reactive types")
	void testUppercaseThenReverseReactive() {
		String input = "Spring Cloud";
		String expectedOutput = "DUOLC GNIRPS";

		Function<Flux<String>, Flux<String>> upperThenReverse = catalog.lookup(Function.class, "uppercase|reverseReactive");

		StepVerifier.create(upperThenReverse.apply(Flux.just(input)))
				.expectNextMatches(actualOuput -> actualOuput.equals(expectedOutput))
				.verifyComplete();
	}
}
