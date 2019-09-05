package br.com.meatApp;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.meatApp.domain.MenuItem;
import br.com.meatApp.domain.Restaurant;
import br.com.meatApp.domain.User;
import br.com.meatApp.repositories.MenuItemRepository;
import br.com.meatApp.repositories.RestaurantRepository;
import br.com.meatApp.repositories.UserRepository;

@SpringBootApplication
public class MeatAppApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository; 
	private RestaurantRepository restaurantRepository;
	private MenuItemRepository menuItemRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(MeatAppApplication.class, args);
	}
	
	public void run(String... args) throws Exception{
		User user1 = new User(1, "João", "joaopauloguedesSilveira@hotmail.com", "123");
		User user2 = new User(2, "Luiz", "LuizAcdc@hotmail.com", "123");
		
		Restaurant rest1 = new Restaurant(1, "Coxinha's Bar", "Vendedor de coxinha", "30 min", 5.0, "Insira a imagem de uma coxinha", "24hrs", "A melhor coxinha da rua");
		
		MenuItem mi1 = new MenuItem(1, "a", "a ", 651.1 , "nsei", rest1);
		
		
		menuItemRepository.save(mi1);
		restaurantRepository.save(rest1);
		userRepository.saveAll(Arrays.asList(user1, user2));
	}
	

}
