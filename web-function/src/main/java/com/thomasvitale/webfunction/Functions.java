package com.thomasvitale.webfunction;

import java.util.function.Consumer;
import java.util.function.Function;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class Functions {

	@Bean
	public Consumer<String> print() {
		return System.out::println;
	}

	@Bean
	public Function<String,String> uppercase() {
		return message -> {
			String toUppercase = message.toUpperCase();
			log.info("Converting {} to uppercase: {}", message, toUppercase);
			return toUppercase;
		};
	}

	@Bean
	public Function<Flux<String>,Flux<String>> reverseReactive() {
		return flux -> flux
				.map(message -> new StringBuilder(message).reverse().toString());
	}

	@Bean
	public Function<Message,String> reverseWithMessage() {
		return message -> {
			String toReversed = new StringBuilder(message.getContent()).reverse().toString();
			log.info("Reversing {}: {}", message, toReversed);
			return toReversed;
		};
	}

	@Bean
	public Function<String,String> reverse() {
		return message -> {
			String toReversed = new StringBuilder(message).reverse().toString();
			log.info("Reversing {}: {}", message, toReversed);
			return toReversed;
		};
	}
}

@Data @AllArgsConstructor @NoArgsConstructor
class Message {
	private String content;
}
