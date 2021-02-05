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
@RequestMapping("/users")
public class PostController {

    @GetMapping("{userID}/posts")
    public ResponseEntity getPostsByUserID(@RequestHeader(value = "Access-Key", required = false) String accessKey,
                                          @PathVariable String userID) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("{userID}/posts")
    public ResponseEntity addPostsByUserID(@RequestHeader(value = "Access-Key", required = false) @AccessKeyNonNull String accessKey,
                                           @PathVariable String userID) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{userID}/posts")
    public ResponseEntity deletePostsByUserID(@RequestHeader(value = "Access-Key", required = false) @AccessKeyNonNull String accessKey,
                                              @PathVariable String userID) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("posts/{postID}")
    public ResponseEntity getPostsByPostID(@RequestHeader(value = "Access-Key", required = false) String accessKey,
                                          @PathVariable String postID) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("posts/{postID}")
    public ResponseEntity updatePostByPostID(@RequestHeader(value = "Access-Key", required = false) @AccessKeyNonNull String accessKey,
                                             @PathVariable String postID) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("posts/{postID}")
    public ResponseEntity patchPostByPostID(@RequestHeader(value = "Access-Key", required = false) @AccessKeyNonNull String accessKey,
                                            @PathVariable String postID) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("posts/{postID}")
    public ResponseEntity deletePostByPostID(@RequestHeader(value = "Access-Key", required = false) @AccessKeyNonNull String accessKey,
                                             @PathVariable String postID) {
        return ResponseEntity.ok().build();
    }
}
