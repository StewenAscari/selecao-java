package br.com.selecaojava.services;

import java.io.IOException; 
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import br.com.selecaojava.domain.PriceHistoric;
import br.com.selecaojava.domain.Sales;
import br.com.selecaojava.domain.User;
import br.com.selecaojava.dto.PriceHistoricDTO;
import br.com.selecaojava.dto.SalesDTO;
import br.com.selecaojava.dto.ModelCSVDTO;
import br.com.selecaojava.repositories.PriceHistoricRepository;
import br.com.selecaojava.repositories.SalesRepository;
import br.com.selecaojava.servives.exceptions.ObjectNotFoundException;

@Service
public class SalesServices {
	
	@Autowired
	private SalesRepository vendasRepository;
	private PriceHistoricService re;
	
	public void insert(List<Sales> vendas) {
		vendasRepository.saveAll(vendas);
		List<PriceHistoric> p = new ArrayList<PriceHistoric>();
		for(Sales s: vendas) {

    		PriceHistoric price = new PriceHistoric(s.getRevenda(), s.getValordeVenda());
    		p.add(price);
		}
		re.insert(p);
		
		
	}
	public void delete(Integer id) throws ObjectNotFoundException {
		find(id);
		try {
			vendasRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possivevel excluir um User");
		}
	}
	public List<Sales> findAll() {
		return vendasRepository.findAll();
	}
	
	public Sales fromDTO(SalesDTO objDto) {
		return new Sales(objDto.getId(), objDto.getRegiaoSigla(),objDto.getEstadoSigla(), objDto.getMunicipio(), objDto.getRevenda(), objDto.getCnpjdaRevenda(),
				objDto.getProduto(), objDto.getDatadaColeta(), objDto.getValordeVenda(), objDto.getValordeCompra(), objDto.getUnidadedeMedida(), 
				objDto.getBandeira());
	}
	
	public Sales update(Sales obj) throws ObjectNotFoundException {	
		Sales newObj = find(obj.getId());
		updateData(newObj,obj);
		return vendasRepository.save(obj);
	}
	private void updateData(Sales newObj,Sales obj) {
		newObj.setRegiaoSigla(obj.getRegiaoSigla());
		newObj.setEstadoSigla(obj.getEstadoSigla());
		newObj.setMunicipio(obj.getMunicipio());
		newObj.setRevenda(obj.getRevenda());
		newObj.setCnpjdaRevenda(obj.getCnpjdaRevenda());
		newObj.setProduto(obj.getProduto());
		newObj.setDatadaColeta(obj.getDatadaColeta());
		newObj.setValordeVenda(obj.getValordeVenda());
		newObj.setValordeCompra(obj.getValordeCompra());
		newObj.setUnidadedeMedida(obj.getUnidadedeMedida());
		newObj.setBandeira(obj.getBandeira());
	}
//	private static final CsvMapper mapper = new CsvMapper();

	public List<Sales> findBysigla(String sigla) {
		return vendasRepository.findByregiaoSigla(sigla);
	}

	public Sales find(Integer id) {
		Sales imageModel = vendasRepository.findById(id).orElse(null);
		return imageModel;
	}
}
