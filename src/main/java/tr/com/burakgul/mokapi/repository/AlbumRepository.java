package tr.com.burakgul.mokapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tr.com.burakgul.mokapi.model.Album;

public interface AlbumRepository extends MongoRepository<Album,String> {
}
