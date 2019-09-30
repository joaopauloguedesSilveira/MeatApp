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
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.meatApp.domain.MenuItem;
import br.com.meatApp.services.MenuItemService;




@RestController
@RequestMapping(value="menuitems")
public class MenuItemResource {

	@Autowired
	private MenuItemService menuItemService;
	
	@RequestMapping(method=RequestMethod.GET) 
	public ResponseEntity<List<MenuItem>> findAll(){ 
		List<MenuItem> menuitems = menuItemService.findAll(); 
		return ResponseEntity.ok().body(menuitems);
	}
	
	@RequestMapping(value="id/{id}" ,method=RequestMethod.GET) 
	public ResponseEntity<MenuItem> findById(@PathVariable Integer id){ 
		MenuItem menuItem = menuItemService.findByID(id);
		return ResponseEntity.ok().body(menuItem);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<MenuItem> insert(@Valid @RequestBody MenuItem menuItem){
		menuItem = menuItemService.insert(menuItem);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/id/{id}")
				.buildAndExpand(menuItem.getId())
				.toUri();
		return ResponseEntity.created(uri).body(menuItem);	
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/id/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody MenuItem menuItem, @PathVariable Integer id){
		menuItem.setId(id);
		menuItem = menuItemService.update(menuItem, id);
		return ResponseEntity.noContent().build();	
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/id/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		menuItemService.delete(id);
		return ResponseEntity.noContent().build();	
	}

	
}
