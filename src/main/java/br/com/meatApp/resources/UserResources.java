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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.meatApp.domain.User;
import br.com.meatApp.services.UserService;

public class UserResources {

	@Autowired
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<User>> findAll(){
		List<User> users = userService.findAll();
		return ResponseEntity.ok().body(users);
	}
	
	@RequestMapping(value="id/{id}" ,method=RequestMethod.GET)
	public ResponseEntity<User> findById(@PathVariable Integer id){
		User user = userService.FindById(id);
		return ResponseEntity.ok().body(user);
	}
	
	@RequestMapping(value="/email",method=RequestMethod.GET)
	public ResponseEntity<User> findByEmail(@RequestParam(value="email") String email){
		User user = userService.FindByEmail(email);
		return ResponseEntity.ok().body(user);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<User> insert(@Valid @RequestBody User user){
		user = userService.InsertUser(user);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(user.getId())
				.toUri();
		return ResponseEntity.created(uri).body(user);
	}
	
	@RequestMapping(value="id/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer id,@Valid @RequestBody User user){
		user = userService.update(user, id);
		return ResponseEntity.noContent().build();
	}
	
}
