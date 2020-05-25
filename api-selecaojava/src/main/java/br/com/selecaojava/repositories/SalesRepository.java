package br.com.selecaojava.repositories;
import br.com.selecaojava.domain.Sales;
import br.com.selecaojava.dto.ModelCSVDTO;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.OrderBy;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.google.common.base.Strings;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Integer> {
	
	List<Sales> findByregiaoSigla(String regiaoSigla);

	

}
