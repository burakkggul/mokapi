package tr.com.burakgul.mokapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class MokapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MokapiApplication.class, args);
	}

}
