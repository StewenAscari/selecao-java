package br.com.selecaojava.dto;

import javax.persistence.Column;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

import br.com.selecaojava.domain.Vendas;

public class VendasDTO {

	@CsvBindByName(column = "Região - Sigla")
	@Column(columnDefinition = "VARCHAR(1000)")
	private String regiaoSigla;
	
	@CsvBindByName(column = "Estado - Sigla")
	private String estadoSigla;
	
	@CsvBindByName(column = "Município")
	private String municipio;
	
	@CsvBindByName(column = "Revenda")
	private String revenda;
	
	@CsvBindByName(column = "CNPJ da Revenda")
	private String cnpjdaRevenda;
	
	@CsvBindByName(column = "Produto")
	private String produto;
	
	@CsvBindByName(column = "Data da Coleta")
	private String datadaColeta;
	
	@CsvBindByName(column = "Valor de Venda")
	private String valordeVenda;
	
	@CsvBindByName(column = "Valor de Compra")
	private String valordeCompra;
	
	@CsvBindByName(column = "Unidade de Medida")
	private String unidadedeMedida;
	
	@CsvBindByName(column = "Bandeira")
	private String bandeira;
	
	private Integer id;
	
	public VendasDTO() {
		
	}
	public VendasDTO(Vendas vendas) {
		regiaoSigla = vendas.getRegiaoSigla();
		estadoSigla = vendas.getEstadoSigla();
		municipio = vendas.getMunicipio();
		revenda = vendas.getRevenda();
		cnpjdaRevenda = vendas.getCnpjdaRevenda();
		produto = vendas.getProduto();
		datadaColeta = vendas.getDatadaColeta();
		valordeVenda = vendas.getValordeVenda();
		valordeCompra = vendas.getValordeCompra();
		unidadedeMedida = vendas.getUnidadedeMedida();
		bandeira = vendas.getBandeira();
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
