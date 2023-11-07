package A1B1O3.bodyrecord;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class StructureApplication {

    public static void main(String[] args) {
        SpringApplication.run(StructureApplication.class, args);
    }

}
