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

import br.com.meatApp.domain.MenuItem;
import br.com.meatApp.services.MenuItemService;

public class MenuItemResources {
	@Autowired
	private MenuItemService MenuItemService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<MenuItem>> findAll(){
		List<MenuItem> menuItem = MenuItemService.findAll();
		return ResponseEntity.ok().body(menuItem);
	}
	
	@RequestMapping(value="id/{id}" ,method=RequestMethod.GET)
	public ResponseEntity<MenuItem> findById(@PathVariable Integer id){
		MenuItem menuItem = MenuItemService.FindById(id);
		return ResponseEntity.ok().body(menuItem);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<MenuItem> insert(@Valid @RequestBody MenuItem menuItem){
		menuItem = MenuItemService.InsertUser(menuItem);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(menuItem.getId())
				.toUri();
		return ResponseEntity.created(uri).body(menuItem);
	}
	
	@RequestMapping(value="id/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer id,@Valid @RequestBody MenuItem menuItem){
		menuItem = MenuItemService.update(menuItem, id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		MenuItemService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
