package tr.com.burakgul.mokapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tr.com.burakgul.mokapi.model.User;

public interface UserRepository extends MongoRepository<User,String> {
}
