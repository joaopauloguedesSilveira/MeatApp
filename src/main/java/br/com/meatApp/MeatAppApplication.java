package br.com.meatApp;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.meatApp.domain.User;
import br.com.meatApp.repositories.UserRepository;

@SpringBootApplication
public class MeatAppApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository; 
	
	public static void main(String[] args) {
		SpringApplication.run(MeatAppApplication.class, args);
	}
	
	public void run(String... args) throws Exception{
		User user1 = new User(1, "João", "joaopauloguedesSilveira@hotmail.com", "123");
		User user2 = new User(2, "Luiz", "LuizAcdc@hotmail.com", "123");
		
		userRepository.saveAll(Arrays.asList(user1, user2));
	}
	

}
