package br.com.selecaojava.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import br.com.selecaojava.domain.Vendas;
import br.com.selecaojava.dto.VendasDTO;
import br.com.selecaojava.repositories.VendasRepository;

@Service
public class VendasServices {
	
	@Autowired
	private VendasRepository vendasRepository;
	
	public void insert(List<Vendas> vendas) {
		//obj.setId(null);
		vendasRepository.saveAll(vendas);
	}
//	private static final CsvMapper mapper = new CsvMapper();

	public List<Vendas> findAll(String sigla) {
		return vendasRepository.findByregiaoSigla(sigla);
	}
}
