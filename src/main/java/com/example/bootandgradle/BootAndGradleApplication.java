package com.example.bootandgradle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
@RestController(value = "/")
@EnableWebFlux
public class BootAndGradleApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootAndGradleApplication.class, args);
	}

	@GetMapping("/hello")
	public ResponseEntity<?> helloWorld() {
		return new ResponseEntity<>("Hello World!", HttpStatus.OK);
	}

	@GetMapping("/numbers")
	public Flux<Integer> getNumbers() {
		Integer[] numbers = {10, 20, 3, 4, 5, 6, 7, 8, 9, 10};
		return Flux.just(numbers).delayElements(Duration.of(1, ChronoUnit.SECONDS)).log();
	}

}
