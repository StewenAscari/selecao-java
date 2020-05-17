
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

import br.com.selecaojava.domain.Useer;
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
	
	public Useer find(Integer id) throws ObjectNotFoundException {
		UserSS user = UserServiceService.authenticated();
		if (user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}		
		Optional<Useer> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Useer.class.getName()));
	}
	
	@Transactional
	public Useer insert(Useer obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Useer update(Useer obj) throws ObjectNotFoundException {	
		Useer newObj = find(obj.getId());
		updateData(newObj,obj);
		return repo.save(obj);
	}
	
	public Useer update(PasswordDTO obj) throws ObjectNotFoundException {		
		Useer newObj = find(obj.getId());
		updatePassword(newObj,obj);
		return repo.save(newObj);
	}
	
	public Useer update(NomeDTO obj) throws ObjectNotFoundException {		
		Useer newObj = find(obj.getId());
		updateNome(newObj,obj);
		return repo.save(newObj);
	}
	
	private void updateNome(Useer newObj, NomeDTO obj) {
		newObj.setId(obj.getId());
		newObj.setNome(obj.getNome());
	}
	
	public NomeDTO nomeDTO(NomeDTO nomeDto) {
		return new NomeDTO(nomeDto.getId(), nomeDto.getNome());
	}	

	public PasswordDTO passwordDTO(PasswordDTO objDto) {		
		return new PasswordDTO(objDto.getId(), pe.encode(objDto.getSenha()));
	}
	
	private void updatePassword(Useer newObj,PasswordDTO obj) {
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

	public List<Useer> findAll() {
		return repo.findAll();
	}
	
	public Page<Useer> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage , Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Useer fromDTO(UserDTO objDto) {
		return new Useer(objDto.getId(),objDto.getNome(), objDto.getEmail(), pe.encode(objDto.getSenha()));
	}
	
	private void updateData(Useer newObj,Useer obj) {
		newObj.setNome(obj.getNome());
	}
	
	public Useer findByEmail(String email){
		
		UserSS user = UserServiceService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}
		Useer obj = repo.findByEmail(email);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Useer.class.getName());
		}
		return obj;
	}
	
}

