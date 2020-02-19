package pl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.controller.StudentController;

@SpringBootApplication
@ComponentScan(basePackageClasses= StudentController.class)
@ComponentScan({"pl"})
@EntityScan("pl")
@EnableJpaRepositories("pl")
public class ApplicationDiary {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationDiary.class, args);
	}

}
