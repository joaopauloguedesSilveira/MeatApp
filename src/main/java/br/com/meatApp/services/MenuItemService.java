package br.com.meatApp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import br.com.meatApp.domain.MenuItem;
import br.com.meatApp.repositories.MenuItemRepository;
import br.com.meatApp.services.exception.DataIntegrityException;
import br.com.meatApp.services.exception.ObjectNotFoundException;

public class MenuItemService {


	@Autowired
	private MenuItemRepository menuItemRepository;
	
	
	public List<MenuItem> findAll(){
		return menuItemRepository.findAll();
	}
	
	public MenuItem FindById(Integer id) {
		Optional<MenuItem> user = menuItemRepository.findById(id);
		return user.orElseThrow(() ->
			new ObjectNotFoundException("Item de Menu não encontrado" + id) );
	}
	
	
	public MenuItem InsertUser(MenuItem menuItem) {
		menuItem.setId(null);
		return menuItemRepository.save(menuItem);
	}
	
	public MenuItem update(MenuItem menuItem, Integer id) {
		menuItem.setId(id);
		return menuItemRepository.save(menuItem);
	}
	
	public void delete(Integer id) {
		try {
			menuItemRepository.deleteById(id);
			
		}catch(DataIntegrityViolationException e ) {
			throw new DataIntegrityException("Ocorreu um erro de integridade! O usuário já possuí pedidos!");
		}
	}
}
