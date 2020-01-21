package pl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import pl.controller.StudentController;

@SpringBootApplication
@ComponentScan(basePackageClasses= StudentController.class)
public class DziennikApplication {

	public static void main(String[] args) {
		SpringApplication.run(DziennikApplication.class, args);
	}

}
