package com.example.bootandgradle;

import com.example.bootandgradle.entity.Todo;
import com.example.bootandgradle.repository.TodoRepository;
import com.example.bootandgradle.service.TodoServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class TodoUnitTest {

	@Mock
	private TodoRepository todoRepository;
	@InjectMocks
	private TodoServiceImpl todoService;

	@Test
	@DisplayName("Test 1: Save a todo Item")
	void saveATodoItem() {
//		Arrange
		Todo todo = new Todo(1, "Buy Ticket", "buy from website");
		Mockito.when(todoRepository.save(Mockito.any(Todo.class))).thenReturn(Mono.just(todo));
//		Act
		Todo savedtodo = todoService.saveTodo(todo).block();
//		Assert
		assert savedtodo != null;
		assertThat(savedtodo.getId()).isNotNull();

	}
}
