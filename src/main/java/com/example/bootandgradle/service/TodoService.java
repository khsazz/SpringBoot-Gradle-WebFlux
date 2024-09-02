package com.example.bootandgradle.service;

import com.example.bootandgradle.entity.Todo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TodoService {
	Mono<Todo> findTodoById(int id);
	Mono<Todo> searchTodoByTitle(String title);
	Flux<Todo> findAllTodos();
	Mono<Todo> saveTodo(Todo todo);
	Mono<Void> deleteTodoById(int id);
}
