package br.com.meatApp.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.meatApp.domain.Restaurant;
import br.com.meatApp.services.RestaurantService;

public class RestaurantResources {
	
	@Autowired
	private RestaurantService RestaurantService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Restaurant>> findAll(){
		List<Restaurant> users = RestaurantService.findAll();
		return ResponseEntity.ok().body(users);
	}
	
	@RequestMapping(value="id/{id}" ,method=RequestMethod.GET)
	public ResponseEntity<Restaurant> findById(@PathVariable Integer id){
		Restaurant user = RestaurantService.FindById(id);
		return ResponseEntity.ok().body(user);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Restaurant> insert(@Valid @RequestBody Restaurant restaurant){
		restaurant = RestaurantService.InsertUser(restaurant);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(restaurant.getId())
				.toUri();
		return ResponseEntity.created(uri).body(restaurant);
	}
	
	@RequestMapping(value="id/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer id,@Valid @RequestBody Restaurant restaurant){
		restaurant = RestaurantService.update(restaurant, id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		RestaurantService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
