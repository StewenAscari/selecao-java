package br.com.selecaojava.dto;

import javax.persistence.Column;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

import br.com.selecaojava.domain.Sales;
import br.com.selecaojava.domain.User;

public class ModelCSVDTO {

	@Column(columnDefinition = "VARCHAR(1000)")
	@CsvBindByPosition(position = 0)
	private String regiaoSigla;
	
	@CsvBindByPosition(position = 1)
	private String estadoSigla;
	
	@CsvBindByPosition(position = 2)
	private String municipio;
	
	@CsvBindByPosition(position = 3)
	private String revenda;
	
	@CsvBindByPosition(position = 4)
	private String cnpjdaRevenda;
	
	@CsvBindByPosition(position = 5)
	private String produto;
	
	@CsvBindByPosition(position = 6)
	private String datadaColeta;
	
	@CsvBindByPosition(position = 7)
	private String valordeVenda;
	
	@CsvBindByPosition(position = 8)
	private String valordeCompra;
	
	@CsvBindByPosition(position = 9)
	private String unidadedeMedida;
	
	@CsvBindByPosition(position = 10)
	private String bandeira;
	
	public ModelCSVDTO() {
		
	}
	public ModelCSVDTO(String regiaoSigla, String stadoSigla, String municipio, String revenda, String revendaCNPJ, String produto, String dataColeta,
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
