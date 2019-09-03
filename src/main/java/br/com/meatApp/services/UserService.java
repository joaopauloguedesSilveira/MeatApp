package br.com.meatApp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meatApp.domain.User;
import br.com.meatApp.repositories.UserRepository;
import br.com.meatApp.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User FindById(Integer id) {
		Optional<User> user = userRepository.findById(id);
		return user.orElseThrow(() ->
			new ObjectNotFoundException("Usuário não encontrado" + id) );
	}
	
	public User FindByEmail(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		return user.orElseThrow(() -> 
				new ObjectNotFoundException("Email não encontrado!"));
	}
	
	public User InsertUser(User user) {
		user.setId(null);
		return userRepository.save(user);
	}
	
	public User update(User user, Integer id) {
		user.setId(id);
		return userRepository.save(user);
	}
	
}
