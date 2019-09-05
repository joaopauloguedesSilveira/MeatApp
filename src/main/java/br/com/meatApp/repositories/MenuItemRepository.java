package br.com.meatApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.meatApp.domain.MenuItem;


@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer>{
	
	
}
