package tr.com.burakgul.mokapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tr.com.burakgul.mokapi.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {
    List<User> findByIsReadOnly(Boolean isReadOnly);
    List<User> findByDataKeyContainsAndIsReadOnly(String dataKey,Boolean isReadOnly);
    List<User> findByDataKeyContains(String dataKey);
    Optional<User> findByIdAndIsReadOnly(String id, Boolean isReadOnly);
    Optional<User> findByIdAndDataKeyContains(String id, String dataKey);
    void deleteAllByIsReadOnly(Boolean isReadOnly);
    void deleteAllByDataKeyContains(String accessKey);
    void deleteByIdAndIsReadOnly(String id, Boolean isReadOnly);
    void deleteByIdAndDataKeyContains(String id, String accessKey);
}
