package com.example.bootandgradle.service;


import com.example.bootandgradle.entity.Todo;
import com.example.bootandgradle.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TodoServiceImpl implements TodoService {

	private final TodoRepository todoRepository;

	@Autowired
	public TodoServiceImpl(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	@Override
	public Mono<Todo> findTodoById(int id) {
		Mono<Todo> todo = todoRepository.findById(id).switchIfEmpty(Mono.error(new Exception(String.format("Todo not found. ID: %s", id))));
		return todo;
	}

	@Override
	public Mono<Todo> searchTodoByTitle(String title) {
		return todoRepository.findTodoByTitle(title);
	}

	@Override
	public Flux<Todo> findAllTodos() {
		return todoRepository.findAll();
	}

	@Override
	public Mono<Todo> saveTodo(Todo todo) {
		return todoRepository.save(todo);
	}

	@Override
	public Mono<Void> deleteTodoById(int id) {
		return todoRepository.deleteById(id);
	}
}
