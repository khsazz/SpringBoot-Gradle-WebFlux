package com.example.bootandgradle.controller;

import com.example.bootandgradle.entity.Todo;
import com.example.bootandgradle.service.TodoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Description;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@WebFluxTest(TodoController.class)
public class TodoControllerUnitTest {

	private final Todo itemToAdd = Todo.builder()
			.id(12)
			.title("dummy todo")
			.description("dummy string").build();

	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private TodoService todoService;

	@Test
	@Description("Reactive Rest Controller Unit Test")
	public void whenAddedTodoThenControlFlowCorrect() throws Exception {
//		Arrange
		Mockito.when(todoService.saveTodo(Mockito.any(Todo.class)))
				.thenReturn(Mono.just(itemToAdd));
//		Act
		webTestClient.post().uri("/api/todos/")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(itemToAdd)
				.exchange()
				.expectStatus().isCreated()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.id").isEqualTo(itemToAdd.getId());

//		Assert
		Mockito.verify(todoService, Mockito.times(1)).saveTodo(Mockito.any(Todo.class));
	}
}
