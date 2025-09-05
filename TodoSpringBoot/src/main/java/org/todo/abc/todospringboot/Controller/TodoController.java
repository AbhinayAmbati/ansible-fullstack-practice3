package org.todo.abc.todospringboot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.todo.abc.todospringboot.Service.TodoService;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "*")
public class TodoController {

    @Autowired
    TodoService todoService;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllTodos() {
        return todoService.getAllTodos();
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createTodo() {
        return todoService.createTodo();
    }

    @PostMapping("/create-custom")
    public ResponseEntity<Object> createCustomTodo(
            @RequestParam String title,
            @RequestParam(required = false) String description) {
        return todoService.createTodo(title, description);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTodoById(@PathVariable Integer id) {
        return todoService.getTodoById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTodo(
            @PathVariable Integer id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Boolean completed) {
        return todoService.updateTodo(id, title, description, completed);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTodo(@PathVariable Integer id) {
        return todoService.deleteTodo(id);
    }
}
