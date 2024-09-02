package com.example.bootandgradle.repository;

import com.example.bootandgradle.entity.Todo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface TodoRepository extends ReactiveCrudRepository<Todo,Integer> {

	Mono<Todo> findTodoByTitle(String title);
}
