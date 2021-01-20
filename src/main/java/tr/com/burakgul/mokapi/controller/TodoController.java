package tr.com.burakgul.mokapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @GetMapping
    public ResponseEntity getTodos() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getTodoById(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity addTodo() {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity putTodoById(@PathVariable("id") String id) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchTodoById(@PathVariable("id") String id) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTodoById(@PathVariable("id") String id) {
        return ResponseEntity.ok().build();
    }
}
