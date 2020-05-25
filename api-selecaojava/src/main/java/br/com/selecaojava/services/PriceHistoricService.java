package br.com.selecaojava.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.selecaojava.domain.PriceHistoric;
import br.com.selecaojava.domain.Sales;
import br.com.selecaojava.dto.PriceHistoricDTO;
import br.com.selecaojava.repositories.PriceHistoricRepository;
import br.com.selecaojava.servives.exceptions.ObjectNotFoundException;

@Service
public class PriceHistoricService {

	private PriceHistoricRepository priceHistoricRepository;
	
	public PriceHistoric find(Integer id) {
		PriceHistoric imageModel = priceHistoricRepository.findById(id).orElse(null);
		return imageModel;
	}
	
	public void insert(List<PriceHistoric> historic) {
		priceHistoricRepository.saveAll(historic);
	}
	
	public PriceHistoric fromDTO(PriceHistoricDTO objDto) {
		return new PriceHistoric(objDto.getRevenda(), objDto.getValue());
	}
	
	public PriceHistoric update(PriceHistoric obj) throws ObjectNotFoundException {	
		PriceHistoric newObj = find(obj.getId());
		updateData(newObj,obj);
		return priceHistoricRepository.save(obj);
	}
	
	private void updateData(PriceHistoric newObj,PriceHistoric obj) {
		newObj.setRevenda(obj.getRevenda());
		newObj.setValor(obj.getValor());
	}
	
	public void delete(Integer id) throws ObjectNotFoundException {
		find(id);
		try {
			priceHistoricRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possivevel excluir um User");
		}
	}
	
	public List<PriceHistoric> findAll() {
		return priceHistoricRepository.findAll();
	}

	public void insertAll(List<Sales> salesDB) {
		List<PriceHistoric>historic = new ArrayList<PriceHistoric>();
		for (Sales s: salesDB) {
			PriceHistoric p = new PriceHistoric(s.getRevenda(), s.getValordeVenda());
			historic.add(p);
		}
		this.insert(historic);
		
	}
	
	
}
