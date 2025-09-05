package org.todo.abc.todospringboot.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.todo.abc.todospringboot.Model.Todo;

@Repository
public interface TodoRepo extends JpaRepository<Todo,Integer> {
}
