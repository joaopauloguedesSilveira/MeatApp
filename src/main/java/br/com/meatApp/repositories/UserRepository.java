package br.com.meatApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.meatApp.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	
}
