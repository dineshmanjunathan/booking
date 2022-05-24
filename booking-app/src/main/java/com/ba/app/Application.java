package com.ba.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import com.ba.app.entity.User;
import com.ba.app.model.UserRepository;

@SpringBootApplication
@ComponentScan("com.ba.app")
@EnableJpaRepositories
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Component
	class DemoCommandLineRunner implements CommandLineRunner {

		@Autowired
		private UserRepository userRepository;

		@Override
		public void run(String... args) throws Exception {

			User u = userRepository.findByUserIdIgnoreCase("ADMIN");
			if (u!=null && u.getId() != null) {
				//do nothing
			}else {
				User user = new User();
				user.setUserId("ADMIN");
				user.setRole("ADMIN");
				user.setName("Administrator");
				user.setPassword("admin@123");
				user.setMemberStatus("A");
				userRepository.save(user);
			}
		}
	}
}
