package tr.com.burakgul.mokapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tr.com.burakgul.mokapi.model.Photo;

public interface PhotoRepository extends MongoRepository<Photo,String> {
}
