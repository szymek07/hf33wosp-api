package pl.sp6pat.ham.hf33wosp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Hf33WospApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(Hf33WospApiApplication.class, args);
	}

}
