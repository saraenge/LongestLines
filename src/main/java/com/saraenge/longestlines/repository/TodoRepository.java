package com.saraenge.longestlines.repository;

import com.saraenge.longestlines.model.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Integer> {
}
