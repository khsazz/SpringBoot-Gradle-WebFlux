package com.example.bootandgradle;

import com.example.bootandgradle.entity.Todo;
import com.example.bootandgradle.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@DataR2dbcTest
public class TodoRepositoryTest {

	@Autowired
	private TodoRepository todoRepository;

	@Test
	public void whenFindTodoByTitle_thenReturnTodo() {
		Todo sample = Todo.builder().title("Sample Todo").id(1).description("wow").build();

		Mono<Todo> savedItem = todoRepository.save(sample)
				.flatMap(todo -> todoRepository.findTodoByTitle(todo.getTitle()));

		StepVerifier.create(savedItem)
				.expectNextMatches(foundItem -> foundItem.getTitle().equals(sample.getTitle()))
				.verifyComplete();
	}
}
