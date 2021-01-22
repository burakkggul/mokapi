package tr.com.burakgul.mokapi.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import tr.com.burakgul.mokapi.model.User;
import tr.com.burakgul.mokapi.repository.UserRepository;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Component
public class MockDataInitUtil {

    private final UserRepository userRepository;

    Resource userResource = new ClassPathResource("mockdata/user.json");

    public MockDataInitUtil(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> userDocumentsInitByFile(String accessKey) throws IOException {
        List<User> users = readUsersByResource();
        //expireDate = now + 5 days
        Date expireDate = new Date(System.currentTimeMillis()+432000000);
        users.forEach(u -> {
            u.setDataKey(accessKey);
            u.setExpireDate(expireDate);
            u.setIsReadOnly(false);
        });
        userRepository.saveAll(users);
        return users;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initReadOnlyData() throws IOException {
        List<User> readOnlyUser = userRepository.findByIsReadOnly(true);
        if (readOnlyUser.size() == 0) {
            List<User> users = readUsersByResource();
            users.forEach(u -> u.setIsReadOnly(true));
            userRepository.saveAll(users);
        }
    }

    public List<User> readUsersByResource() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readValue(userResource.getInputStream(), JsonNode.class);
        ObjectReader reader = mapper.readerFor(new TypeReference<List<User>>() {});
        return reader.readValue(jsonNode);
    }
}
