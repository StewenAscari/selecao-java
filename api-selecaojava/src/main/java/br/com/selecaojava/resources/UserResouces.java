package br.com.selecaojava.resources;

import java.net.URI; 
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.selecaojava.domain.User;
import br.com.selecaojava.dto.NomeDTO;
import br.com.selecaojava.dto.PasswordDTO;
import br.com.selecaojava.dto.UserDTO;
import br.com.selecaojava.services.UserService;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/user")
public class UserResouces {
	
	@Autowired
	private UserService service;
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> find(@PathVariable Integer id){
		User obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/email", method=RequestMethod.GET)
	public ResponseEntity<User> find(@RequestParam(value="value") String email) {
		User obj = service.findByEmail(email);
		return ResponseEntity.ok().body(obj);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')") //Forces a user is logged in to access this endpoint
	@RequestMapping(method=RequestMethod.POST) //add a new admin
	public ResponseEntity<Void> insert(@Valid @RequestBody UserDTO objDto){
		User obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
				buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}",method=RequestMethod.PUT) //update an entire ADM
	public ResponseEntity<Void> update(@Valid @RequestBody UserDTO objDto,@PathVariable Integer id) throws ObjectNotFoundException{
		User obj = service.fromDTO(objDto);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/password/{id}",method=RequestMethod.PATCH) //update a password ADM
	public ResponseEntity<Void> update(@Valid @RequestBody PasswordDTO passwordDto, @PathVariable Integer id) throws ObjectNotFoundException{
		PasswordDTO objDTO = service.passwordDTO(passwordDto);
		service.update(objDTO);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/name/{id}",method=RequestMethod.PATCH) //update name ADM
	public ResponseEntity<Void> update(@Valid @RequestBody NomeDTO nomeDto, @PathVariable Integer id) throws ObjectNotFoundException{
		NomeDTO objDTO = service.nomeDTO(nomeDto);
		service.update(objDTO);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE) //Delete ADM
	public ResponseEntity<Void> delete(@PathVariable Integer id) throws ObjectNotFoundException {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET) //list all ADM
	public ResponseEntity<List<UserDTO>> findPage() {
		List<User> list = service.findAll();
		List<UserDTO> listDTO = list.stream().map(obj -> new UserDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/page", method = RequestMethod.GET) //lists all paged ADMs
	public ResponseEntity<Page<UserDTO>> findAll(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction){
		Page<User> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<UserDTO> listDTO = list.map(obj -> new UserDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}


}

