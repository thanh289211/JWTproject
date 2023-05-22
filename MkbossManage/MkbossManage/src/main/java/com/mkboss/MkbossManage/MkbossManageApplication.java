package com.mkboss.MkbossManage;

import com.mkboss.MkbossManage.Service.AuthenticationService;
import com.mkboss.MkbossManage.Enum.Role;
import com.mkboss.MkbossManage.Model.UserModel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MkbossManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(MkbossManageApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(AuthenticationService authenticationService){
		return args -> {
			var admin = UserModel.builder()
					.firstName("super")
					.lastName("amin")
					.email("admin@gmail.com")
					.role(Role.ADMIN)
					.password("hello")
					.build();
			authenticationService.register(admin);
		};
	}
}
