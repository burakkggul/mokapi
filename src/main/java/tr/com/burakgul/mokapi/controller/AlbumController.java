package tr.com.burakgul.mokapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.burakgul.mokapi.dto.request.AlbumRequest;
import tr.com.burakgul.mokapi.service.AlbumService;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping
    public ResponseEntity getAlbums() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getAlbumById(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity putAlbums(@RequestBody List<AlbumRequest> albumRequests) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity putAlbumById(@PathVariable("id") String id,
                                       @RequestBody AlbumRequest albumRequest) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchAlbumById(@PathVariable("id") String id,
                                         @RequestBody AlbumRequest albumRequest) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAlbumById(@PathVariable("id") String id,
                                          @RequestBody AlbumRequest albumRequest) {
        return ResponseEntity.ok().build();
    }
}
