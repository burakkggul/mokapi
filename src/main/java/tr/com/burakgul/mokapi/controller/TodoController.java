package tr.com.burakgul.mokapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.burakgul.mokapi.annotation.AccessKeyNonNull;

/**
 * @since 1.0.0
 * @author Burak GÜL
 */
@RestController
@RequestMapping("/users/")
public class TodoController {

    @GetMapping("{userID}/todos")
    public ResponseEntity getTodosByUserID(@RequestHeader(value = "Access-Key", required = false) String accessKey,
                                          @PathVariable String userID) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("{userID}/todos")
    public ResponseEntity addTodosByUserID(@RequestHeader(value = "Access-Key", required = false) @AccessKeyNonNull String accessKey,
                                           @PathVariable String userID) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{userID}/todos")
    public ResponseEntity deleteTodosByUserID(@RequestHeader(value = "Access-Key", required = false) @AccessKeyNonNull String accessKey,
                                              @PathVariable String userID) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("todos/{todoID}")
    public ResponseEntity getTodoByTodoID(@RequestHeader(value = "Access-Key", required = false) String accessKey,
                                          @PathVariable String todoID) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("todos/{todoID}")
    public ResponseEntity updateTodoByTodoID(@RequestHeader(value = "Access-Key", required = false) @AccessKeyNonNull String accessKey,
                                             @PathVariable String todoID) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("todos/{todoID}")
    public ResponseEntity patchTodoByTodoID(@RequestHeader(value = "Access-Key", required = false) @AccessKeyNonNull String accessKey,
                                            @PathVariable String todoID) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("todos/{todoID}")
    public ResponseEntity deleteTodoByTodoID(@RequestHeader(value = "Access-Key", required = false) @AccessKeyNonNull String accessKey,
                                             @PathVariable String todoID) {
        return ResponseEntity.ok().build();
    }
}
