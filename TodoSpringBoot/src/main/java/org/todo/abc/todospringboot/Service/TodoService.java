package org.todo.abc.todospringboot.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.todo.abc.todospringboot.Model.Todo;
import org.todo.abc.todospringboot.Repo.TodoRepo;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    TodoRepo todoRepo;

    public ResponseEntity<Object> getAllTodos() {
        try {
            List<Todo> todos = todoRepo.findAll();
            return ResponseEntity.ok(todos);
        } catch(Exception e) {
            return ResponseEntity.status(500).body("An error occurred while fetching todos: " + e.getMessage());
        }
    }

    public ResponseEntity<Object> createTodo() {
        try {
            Todo newTodo = new Todo();
            newTodo.setTitle("New Todo");
            newTodo.setDescription("Default description");
            Todo savedTodo = todoRepo.save(newTodo);
            return ResponseEntity.status(201).body(savedTodo);
        } catch(Exception e) {
            return ResponseEntity.status(500).body("An error occurred while creating a todo: " + e.getMessage());
        }
    }

    public ResponseEntity<Object> createTodo(String title, String description) {
        try {
            Todo newTodo = new Todo(title, description);
            Todo savedTodo = todoRepo.save(newTodo);
            return ResponseEntity.status(201).body(savedTodo);
        } catch(Exception e) {
            return ResponseEntity.status(500).body("An error occurred while creating a todo: " + e.getMessage());
        }
    }

    public ResponseEntity<Object> getTodoById(Integer id) {
        try {
            Optional<Todo> todo = todoRepo.findById(id);
            if (todo.isPresent()) {
                return ResponseEntity.ok(todo.get());
            } else {
                return ResponseEntity.status(404).body("Todo not found with id: " + id);
            }
        } catch(Exception e) {
            return ResponseEntity.status(500).body("An error occurred while fetching todo: " + e.getMessage());
        }
    }

    public ResponseEntity<Object> updateTodo(Integer id, String title, String description, Boolean completed) {
        try {
            Optional<Todo> existingTodo = todoRepo.findById(id);
            if (existingTodo.isPresent()) {
                Todo todo = existingTodo.get();
                if (title != null) todo.setTitle(title);
                if (description != null) todo.setDescription(description);
                if (completed != null) todo.setCompleted(completed);

                Todo updatedTodo = todoRepo.save(todo);
                return ResponseEntity.ok(updatedTodo);
            } else {
                return ResponseEntity.status(404).body("Todo not found with id: " + id);
            }
        } catch(Exception e) {
            return ResponseEntity.status(500).body("An error occurred while updating todo: " + e.getMessage());
        }
    }

    public ResponseEntity<Object> deleteTodo(Integer id) {
        try {
            Optional<Todo> existingTodo = todoRepo.findById(id);
            if (existingTodo.isPresent()) {
                todoRepo.deleteById(id);
                return ResponseEntity.ok("Todo deleted successfully");
            } else {
                return ResponseEntity.status(404).body("Todo not found with id: " + id);
            }
        } catch(Exception e) {
            return ResponseEntity.status(500).body("An error occurred while deleting todo: " + e.getMessage());
        }
    }
}
