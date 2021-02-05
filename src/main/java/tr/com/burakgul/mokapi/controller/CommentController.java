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
@RequestMapping("/users/posts")
public class CommentController {

    @GetMapping("{postID}/comments")
    public ResponseEntity getCommentsByPostID(@RequestHeader(value = "Access-Key", required = false) String accessKey,
                                           @PathVariable String postID) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("{postID}/comments")
    public ResponseEntity addCommentsByPostID(@RequestHeader(value = "Access-Key", required = false) @AccessKeyNonNull String accessKey,
                                           @PathVariable String postID) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{postID}/comments")
    public ResponseEntity deleteCommentsByPostID(@RequestHeader(value = "Access-Key", required = false) @AccessKeyNonNull String accessKey,
                                              @PathVariable String postID) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("comments/{commentID}")
    public ResponseEntity getCommentByCommentID(@RequestHeader(value = "Access-Key", required = false) String accessKey,
                                           @PathVariable String commentID) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("comments/{commentID}")
    public ResponseEntity updateCommentByCommentID(@RequestHeader(value = "Access-Key", required = false) @AccessKeyNonNull String accessKey,
                                             @PathVariable String commentID) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("comments/{commentID}")
    public ResponseEntity patchCommentByCommentID(@RequestHeader(value = "Access-Key", required = false) @AccessKeyNonNull String accessKey,
                                            @PathVariable String commentID) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("comments/{commentID}")
    public ResponseEntity deleteCommentByCommentID(@RequestHeader(value = "Access-Key", required = false) @AccessKeyNonNull String accessKey,
                                             @PathVariable String commentID) {
        return ResponseEntity.ok().build();
    }
}
