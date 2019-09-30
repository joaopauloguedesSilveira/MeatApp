package br.com.meatApp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.meatApp.domain.User;
/*toda parte do down está pronta, todo crud de user pronto, td referente a tabela de user*/

@Repository
public interface UserRepository extends JpaRepository <User, Integer> {
	
	public Optional<User> findByEmail(String email);

}
