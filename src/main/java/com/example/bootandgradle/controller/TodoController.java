package com.example.bootandgradle.controller;

import com.example.bootandgradle.entity.Todo;
import com.example.bootandgradle.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

	private final TodoService todoService;

	@Autowired
	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}

	@GetMapping("/{id}")
	Mono<ResponseEntity<Todo>> findTodoById(@PathVariable int id) {

		return todoService.findTodoById(id)
				.map(todo -> ResponseEntity.ok(todo));
	}

	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	Flux<Todo> findAllTodos() {
		return todoService.findAllTodos().log();
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	Mono<ResponseEntity<Todo>> saveTodo(@RequestBody Todo todo) {
		return todoService.saveTodo(todo)
				.map(savedTodo -> ResponseEntity.status(HttpStatus.CREATED).body(savedTodo));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	Mono<Void> deleteTodoById(@PathVariable int id) {
		return todoService.deleteTodoById(id);
	}

	@GetMapping("/search")
	Mono<ResponseEntity<Todo>> searchTodoByTitle(@RequestParam String title) {
		return todoService.searchTodoByTitle(title)
				.map(todo -> ResponseEntity.ok(todo))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
}
