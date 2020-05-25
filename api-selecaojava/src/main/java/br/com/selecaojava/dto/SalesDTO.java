package br.com.selecaojava.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.opencsv.bean.CsvBindByPosition;

import br.com.selecaojava.domain.Sales;
import br.com.selecaojava.domain.User;

public class SalesDTO {
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
	
	public SalesDTO(Sales obj) {
		
	}
	public SalesDTO(String regiaoSigla, String stadoSigla, String municipio, String revenda, String revendaCNPJ, String produto, String dataColeta,
	String valorVenda, String valorCompra, String unidadeMedida, String bandeira) {
		
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
		this.datadaColeta = datadaColeta;
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
}
