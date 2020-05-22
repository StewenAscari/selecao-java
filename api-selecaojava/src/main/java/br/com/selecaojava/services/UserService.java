
package br.com.selecaojava.services;

import java.util.List; 
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.selecaojava.domain.User;
import br.com.selecaojava.dto.NomeDTO;
import br.com.selecaojava.dto.PasswordDTO;
import br.com.selecaojava.dto.UserDTO;
import br.com.selecaojava.enums.Perfil;
import br.com.selecaojava.repositories.UserRepository;
import br.com.selecaojava.security.UserSS;
import br.com.selecaojava.servives.exceptions.AuthorizationException;
import br.com.selecaojava.servives.exceptions.ObjectNotFoundException;


@Service
public class UserService {
	
	@Autowired 
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private UserRepository repo;
	
	public User find(Integer id) throws ObjectNotFoundException {
		UserSS user = UserServiceService.authenticated();
		if (user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}		
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + User.class.getName()));
	}
	
	@Transactional
	public User insert(User obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public User update(User obj) throws ObjectNotFoundException {	
		User newObj = find(obj.getId());
		updateData(newObj,obj);
		return repo.save(obj);
	}
	
	public User update(PasswordDTO obj) throws ObjectNotFoundException {		
		User newObj = find(obj.getId());
		updatePassword(newObj,obj);
		return repo.save(newObj);
	}
	
	public User update(NomeDTO obj) throws ObjectNotFoundException {		
		User newObj = find(obj.getId());
		updateNome(newObj,obj);
		return repo.save(newObj);
	}
	
	private void updateNome(User newObj, NomeDTO obj) {
		newObj.setId(obj.getId());
		newObj.setNome(obj.getNome());
	}
	
	public NomeDTO nomeDTO(NomeDTO nomeDto) {
		return new NomeDTO(nomeDto.getId(), nomeDto.getNome());
	}	

	public PasswordDTO passwordDTO(PasswordDTO objDto) {		
		return new PasswordDTO(objDto.getId(), pe.encode(objDto.getSenha()));
	}
	
	private void updatePassword(User newObj,PasswordDTO obj) {
		newObj.setId(obj.getId());
		newObj.setSenha(obj.getSenha());
	}

	public void delete(Integer id) throws ObjectNotFoundException {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possivevel excluir um User");
		}
	}

	public List<User> findAll() {
		return repo.findAll();
	}
	
	public Page<User> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage , Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(),objDto.getNome(), objDto.getEmail(), pe.encode(objDto.getSenha()));
	}
	
	private void updateData(User newObj,User obj) {
		newObj.setNome(obj.getNome());
	}
	
	public User findByEmail(String email){
		
		UserSS user = UserServiceService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}
		User obj = repo.findByEmail(email);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + User.class.getName());
		}
		return obj;
	}
	
}

