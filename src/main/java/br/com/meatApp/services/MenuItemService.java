package br.com.meatApp.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.meatApp.domain.MenuItem;
import br.com.meatApp.exception.DataIntegrityException;
import br.com.meatApp.exception.ObjectNotFoundException;
import br.com.meatApp.repositories.MenuItemRepository;



@Service
public class MenuItemService {
	
	@Autowired
	private MenuItemRepository menuItemRepository;
	
	public List <MenuItem> findAll(){
		return menuItemRepository.findAll();
	}
	
	public MenuItem findByID(Integer id) {
		Optional<MenuItem> menuItem = menuItemRepository.findById(id);
		return menuItem.orElseThrow(() ->
			new ObjectNotFoundException("Item do Menu não encontrado! ID: "+ id)) ;
	}
	
	
	public MenuItem insert(MenuItem menuItem) {
		menuItem.setId(null);
		return menuItemRepository.save(menuItem);
	}
	
	public MenuItem update(MenuItem menuItem, Integer id) {
		return menuItemRepository.save(menuItem);
		
	}
	
	public void delete(Integer id) {
		this.findByID(id);
		try {
			menuItemRepository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Ocorreu um erro de integridade nesse item de menu");
		}

	}

}
