package br.com.selecaojava.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set; 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

@Entity
public class Sales implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "VARCHAR(1000)")
	private String regiaoSigla;
	
	private String estadoSigla;
	
	private String municipio;
	
	private String revenda;
	
	private String cnpjdaRevenda;
	
	private String produto;
	
	private String datadaColeta;
	
	private String valordeVenda;
	
	private String valordeCompra;
	
	private String unidadedeMedida;
	
	private String bandeira;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	private User user;
	public Sales() {
		
	}
	public Sales(String regiaoSigla, String stadoSigla, String municipio, String revenda, String revendaCNPJ, String produto, String dataColeta,
	String valorVenda, String valorCompra, String unidadeMedida, String bandeira, User user) {
		
		this.regiaoSigla = regiaoSigla;
		this.estadoSigla = stadoSigla;
		this.municipio = municipio;
		this.revenda = revenda;
		this.cnpjdaRevenda = revendaCNPJ;
		this.produto = produto;
		this.datadaColeta = dataColeta;
		this.valordeVenda = valorVenda;
		this.valordeCompra = valorCompra;
		this.unidadedeMedida = unidadeMedida;
		this.bandeira = bandeira;
		this.user = user;
	}
	
	public Sales(Integer id, String regiaoSigla, String stadoSigla, String municipio, String revenda, String revendaCNPJ, String produto, String dataColeta,
			String valorVenda, String valorCompra, String unidadeMedida, String bandeira) {
				this.id = id;
				this.regiaoSigla = regiaoSigla;
				this.estadoSigla = stadoSigla;
				this.municipio = municipio;
				this.revenda = revenda;
				this.cnpjdaRevenda = revendaCNPJ;
				this.produto = produto;
				this.datadaColeta = dataColeta;
				this.valordeVenda = valorVenda;
				this.valordeCompra = valorCompra;
				this.unidadedeMedida = unidadeMedida;
				this.bandeira = bandeira;
				this.user = user;
			}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRegiaoSigla() {
		return regiaoSigla;
	}
	public void setRegiaoSigla(String regiaoSigla) {
		this.regiaoSigla = regiaoSigla;
	}
	public String getEstadoSigla() {
		return estadoSigla;
	}
	public void setEstadoSigla(String estadoSigla) {
		this.estadoSigla = estadoSigla;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getRevenda() {
		return revenda;
	}
	public void setRevenda(String revenda) {
		this.revenda = revenda;
	}
	public String getCnpjdaRevenda() {
		return cnpjdaRevenda;
	}
	public void setCnpjdaRevenda(String cnpjdaRevenda) {
		this.cnpjdaRevenda = cnpjdaRevenda;
	}
	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}
	public String getDatadaColeta() {
		return datadaColeta;
	}
	public void setDatadaColeta(String datadaColeta) {
		datadaColeta = datadaColeta;
	}
	public String getValordeVenda() {
		return valordeVenda;
	}
	public void setValordeVenda(String valordeVenda) {
		this.valordeVenda = valordeVenda;
	}
	public String getValordeCompra() {
		return valordeCompra;
	}
	public void setValordeCompra(String valordeCompra) {
		this.valordeCompra = valordeCompra;
	}
	public String getUnidadedeMedida() {
		return unidadedeMedida;
	}
	public void setUnidadedeMedida(String unidadedeMedida) {
		this.unidadedeMedida = unidadedeMedida;
	}
	public String getBandeira() {
		return bandeira;
	}
	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
}
