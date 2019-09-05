package br.com.meatApp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.meatApp.domain.Restaurant;
import br.com.meatApp.repositories.RestaurantRepository;
import br.com.meatApp.services.exception.DataIntegrityException;
import br.com.meatApp.services.exception.ObjectNotFoundException;

@Service
public class RestaurantService {
	
	@Autowired
	private RestaurantRepository RestaurantRepository;
	
	public List<Restaurant> findAll(){
		return RestaurantRepository.findAll();
	}
	
	public Restaurant FindById(Integer id) {
		Optional<Restaurant> user = RestaurantRepository.findById(id);
		return user.orElseThrow(() ->
			new ObjectNotFoundException("Usuário não encontrado" + id) );
	}
	
	public Restaurant InsertUser(Restaurant restaurant) {
		restaurant.setId(null);
		return RestaurantRepository.save(restaurant);
	}
	
	public Restaurant update(Restaurant restaurant, Integer id) {
		restaurant.setId(id);
		return RestaurantRepository.save(restaurant);
	}
	
	public void delete(Integer id) {
		try {
			RestaurantRepository.deleteById(id);
			
		}catch(DataIntegrityViolationException e ) {
			throw new DataIntegrityException("Ocorreu um erro de integridade! O usuário já possuí pedidos!");
		}
	}
}
