package br.com.selecaojava.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.selecaojava.domain.Useer;
import br.com.selecaojava.repositories.UserRepository;
import br.com.selecaojava.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Useer user = repo.findByEmail(email); //Search email database
		
		if(user == null) { //If imail does not exist throw exception
			throw new UsernameNotFoundException("Email não existe: "+email);
		}
		
		return new UserSS(user.getId(),user.getEmail(),user.getSenha(), user.getPerfis());
	}

	
}
