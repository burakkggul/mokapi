package tr.com.burakgul.mokapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tr.com.burakgul.mokapi.model.Comment;

public interface CommentRepository extends MongoRepository<Comment,String> {
}
