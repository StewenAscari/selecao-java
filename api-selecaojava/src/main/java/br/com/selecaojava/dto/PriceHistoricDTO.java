package br.com.selecaojava.dto;

import java.io.Serializable;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.selecaojava.domain.PriceHistoric;

public class PriceHistoricDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String valor;
	private String revenda;
	
	public PriceHistoricDTO() {
			
		}
	

	public PriceHistoricDTO(PriceHistoric priceHistoric) {
		this.valor = valor;
	}

	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getValue() {
		return valor;
	}


	public void setValue(String value) {
		this.valor = value;
	}


	public String getRevenda() {
		return revenda;
	}


	public void setRevenda(String revenda) {
		this.revenda = revenda;
	}
	
	
	
}
