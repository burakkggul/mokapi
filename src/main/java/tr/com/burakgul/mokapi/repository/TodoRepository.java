package tr.com.burakgul.mokapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tr.com.burakgul.mokapi.model.Todo;

public interface TodoRepository extends MongoRepository<Todo,String> {
}
