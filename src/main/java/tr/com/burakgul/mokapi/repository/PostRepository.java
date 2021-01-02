package tr.com.burakgul.mokapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tr.com.burakgul.mokapi.model.Post;

public interface PostRepository extends MongoRepository<Post,String> {
}
