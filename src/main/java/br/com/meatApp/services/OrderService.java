package br.com.meatApp.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meatApp.domain.OrderItem;
import br.com.meatApp.domain.Orders;
import br.com.meatApp.exception.ObjectNotFoundException;
import br.com.meatApp.repositories.OrderItemRepository;
import br.com.meatApp.repositories.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private MenuItemService menuItemService;
	
	public List<Orders> findAll(){
		return orderRepository.findAll();
	}
	
	
	public Orders findById(Integer id) {
		Optional<Orders> order = orderRepository.findById(id);
		return order.orElseThrow(() ->
				new ObjectNotFoundException("Pedido não encontrado! ID:" + id));
	}
	
	
	public Orders insert(Orders order) {
		order.setId(null);
		order.setData(LocalDateTime.now());//obriga a hora ser automatica e atual
		order.setUser(userService.findByID(order.getUser().getId()));
		order.setRestaurant(restaurantService.findByID(order.getRestaurant().getId()));
		order = orderRepository.save(order);
		
		List<OrderItem> items = order.getOrderItems();
		int item = 1;
		for (OrderItem it : items) {
			it.setOrders(order);
			it.setOrderItemId(item);
			it.setMenuItem(menuItemService.findByID(it.getMenuItem().getId()));
			
			orderItemRepository.save(it);
			item++;
			
		}
		
		return order;
	}

}
