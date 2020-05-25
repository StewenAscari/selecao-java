package br.com.selecaojava.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PriceHistoric implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String valor;
	
	private String revenda;
	
	public PriceHistoric() {
		
	}
	
	public PriceHistoric(Integer id, String revenda, String valor) {
		this.id = id;
		this.revenda = revenda;
		this.valor = valor;
	}

	public PriceHistoric(String revenda, String valor) {
		this.revenda = revenda;
		this.valor = valor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getRevenda() {
		return revenda;
	}

	public void setRevenda(String revenda) {
		this.revenda = revenda;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	
}
