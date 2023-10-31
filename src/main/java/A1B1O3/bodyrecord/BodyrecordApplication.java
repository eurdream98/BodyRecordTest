package A1B1O3.bodyrecord;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BodyrecordApplication {

	public static void main(String[] args) {
		SpringApplication.run(BodyrecordApplication.class, args);
	}

}
