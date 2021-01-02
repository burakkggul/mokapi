package tr.com.burakgul.mokapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tr.com.burakgul.mokapi.repository.CommentRepository;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    Logger logger = LoggerFactory.getLogger(CommentService.class);

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
}
