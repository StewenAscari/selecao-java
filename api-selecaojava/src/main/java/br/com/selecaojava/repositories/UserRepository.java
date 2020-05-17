package br.com.selecaojava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.selecaojava.domain.Useer;


@Repository	
public interface UserRepository extends JpaRepository<Useer, Integer>{
	
	@Transactional(readOnly=true)
	Useer findByEmail(String email);
	
}