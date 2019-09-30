package br.com.meatApp;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.meatApp.domain.MenuItem;
import br.com.meatApp.domain.OrderItem;
import br.com.meatApp.domain.OrderItemPK;
import br.com.meatApp.domain.Orders;
import br.com.meatApp.domain.Restaurant;
import br.com.meatApp.domain.User;
import br.com.meatApp.repositories.MenuItemRepository;
import br.com.meatApp.repositories.OrderItemRepository;
import br.com.meatApp.repositories.OrderRepository;
import br.com.meatApp.repositories.RestaurantRepository;
import br.com.meatApp.repositories.UserRepository;

@SpringBootApplication
public class MeatappApplication implements CommandLineRunner{
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private MenuItemRepository menuItemRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(MeatappApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception{
		User user1 = new User(1,"Andre","andremelo123@gmail.com","123");
		User user2 = new User(2,"Saulo","saulogrego@gmail.com","123");
		userRepository.saveAll(Arrays.asList(user1, user2));
		
		
		Restaurant r1 = new Restaurant(1, "Hot Dog", "Podrão", "10 minutos", 
				10.0, "ExampleImage", "17h às 00h", "Lanche aqui é do bão");
		restaurantRepository.saveAll(Arrays.asList(r1));
		
		MenuItem m1 = new MenuItem(1, "Vira Lata", "Vem só salsicha", 5.0, "ImageExample", r1);
		menuItemRepository.saveAll(Arrays.asList(m1));
		
		Orders o1 = new Orders(1,LocalDateTime.now(),user1,r1,"rua A","123",null,"Dinheiro");
		orderRepository.saveAll(Arrays.asList(o1));
		
		OrderItem oi1 = new OrderItem(new OrderItemPK(o1,1),1,5.0,m1);
		orderItemRepository.saveAll(Arrays.asList(oi1));
		
		//post de user, post de pedidos
	}

}
