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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.meatApp.domain.User;
import br.com.meatApp.services.UserService;


@RestController
@RequestMapping(value="users")//mapeia as requisições


public class UserResource {//aqui recebe a requisição e redireciona para o service
	
	
	
	
	
	@Autowired //Inicializa o objeto (Injeção de Variavel /dependência
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.GET) // direciona as requisições para este metodo
	// se chegar na uri uma solicitação para users e essa solicitação for GET direciona
	//a requisição para este metodo que no caso vai entregar a lista de usuários
	public ResponseEntity<List<User>> findAll(){ // ResponseEntity serve para converter
		//a lista de usuarios java em uma entidade de resposta. Torna eles mais genericos 
		//para não ter problema no front end
		List<User> users = userService.findAll(); // pega lista de usuários que esta 
		// no userService
		return ResponseEntity.ok().body(users); //retorna uma Lista de Usuários que foi pega no users
		// so que essa lista foi modificada para um tipo de arquivo mais generico
		//(JSON) para não ter problemas futuros com o front end e o body serve
		//para colocar a lista de usuarios no corpo
	}
	
	@RequestMapping(value="id/{id}" ,method=RequestMethod.GET) //value é a moldura de como
	// o diretorio (caminho) para acesso ao usuário vai ficar.
	public ResponseEntity<User> findById(@PathVariable Integer id){ //@Path para informar O CAMINHO
		//que a variavel vai chegar no meio do caminho.
		User user = userService.findByID(id);
		return ResponseEntity.ok().body(user);//ok é o status 200 que indica ok
	}
	
	@RequestMapping(value="/email",method=RequestMethod.GET)//GET REQUISIÇÃO
	public ResponseEntity<User> findByEmail(@RequestParam (value="email")String email){//se torna parametro
		User user = userService.findByEmail(email);
		return ResponseEntity.ok().body(user);
	}
	
	
	@RequestMapping(method=RequestMethod.POST)//PARA EXPOR O METODO ABAIXO, POST CORPO DA MSN
	public ResponseEntity<User> insert(@Valid @RequestBody User user){//metodo para inserir, ESSA MSN TA VINDO DO CORPO DA MSN
		user = userService.insert(user);//manda e retorna o msm usuario com id 
		//abaixo vai retornar que user foi criado
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/id/{id}")
				.buildAndExpand(user.getId())
				.toUri();//construção da uri/caminho
		
		return ResponseEntity.created(uri).body(user);	
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/id/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody User user, @PathVariable Integer id){
		user.setId(id);//para garantir que não será sem id
		user = userService.update(user, id);
		return ResponseEntity.noContent().build();	
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/id/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		userService.delete(id);
		return ResponseEntity.noContent().build();	
	}

}
