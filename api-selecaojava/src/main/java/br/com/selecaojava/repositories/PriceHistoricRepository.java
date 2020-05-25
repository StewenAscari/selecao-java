package br.com.selecaojava.repositories;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

import br.com.selecaojava.domain.PriceHistoric;

@Repository
public interface PriceHistoricRepository extends JpaRepository<PriceHistoric, Integer> {

}
