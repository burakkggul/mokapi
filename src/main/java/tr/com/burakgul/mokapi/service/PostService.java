package tr.com.burakgul.mokapi.service;

import org.springframework.stereotype.Service;
import tr.com.burakgul.mokapi.repository.PostRepository;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
}
