package br.com.selecaojava.services;

import java.text.ParseException;  
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.selecaojava.domain.User;
import br.com.selecaojava.enums.Perfil;
import br.com.selecaojava.repositories.UserRepository;

@Service
public class DBService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private UserRepository userRepository;
	

	public void instantiateTestDatabase() throws ParseException{
		
		User userAdmin = new User(null,"MainAdmin","admin@gmail.com",pe.encode("admin"));
		userAdmin.addPerfil(Perfil.ADMIN);
		
		userRepository.saveAll(Arrays.asList(userAdmin));
		
	}
}

