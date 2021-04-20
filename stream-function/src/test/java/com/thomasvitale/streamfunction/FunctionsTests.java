package com.thomasvitale.streamfunction;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FunctionsTests {

	private final Functions functions = new Functions();

	@Test
	void testUppercase() {
		String input = "Spring Cloud";
		String expectedOutput = "SPRING CLOUD";

		String actualOutput = functions.uppercase().apply(input);

		assertThat(actualOutput).isEqualTo(expectedOutput);
	}

	@Test
	void testReverse() {
		String input = "Spring Cloud";
		String expectedOutput = "duolC gnirpS";

		String actualOutput = functions.reverse().apply(input);

		assertThat(actualOutput).isEqualTo(expectedOutput);
	}

	@Test
	void testUppercaseThenReverse() {
		String input = "Spring Cloud";
		String expectedOutput = "DUOLC GNIRPS";

		String actualOutput = functions.uppercase().andThen(functions.reverse()).apply(input);

		assertThat(actualOutput).isEqualTo(expectedOutput);
	}
}
