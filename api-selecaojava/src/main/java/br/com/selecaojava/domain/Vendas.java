package br.com.selecaojava.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

@Entity
public class Vendas {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@CsvBindByName(column = "Região - Sigla")
	@Column(columnDefinition = "VARCHAR(1000)")
	@CsvBindByPosition(position = 0)
	private String regiaoSigla;
	
	@CsvBindByName(column = "Estado - Sigla")
	@CsvBindByPosition(position = 1)
	private String estadoSigla;
	
	@CsvBindByName(column = "Município")
	@CsvBindByPosition(position = 2)
	private String municipio;
	
	@CsvBindByName(column = "Revenda")
	@CsvBindByPosition(position = 3)
	private String revenda;
	
	@CsvBindByName(column = "CNPJ da Revenda")
	@CsvBindByPosition(position = 4)
	private String cnpjdaRevenda;
	
	@CsvBindByName(column = "Produto")
	@CsvBindByPosition(position = 5)
	private String produto;
	
	@CsvBindByName(column = "Data da Coleta")
	@CsvBindByPosition(position = 6)
	private String datadaColeta;
	
	@CsvBindByName(column = "Valor de Venda")
	@CsvBindByPosition(position = 7)
	private String valordeVenda;
	
	@CsvBindByName(column = "Valor de Compra")
	@CsvBindByPosition(position = 8)
	private String valordeCompra;
	
	@CsvBindByName(column = "Unidade de Medida")
	@CsvBindByPosition(position = 9)
	private String unidadedeMedida;
	
	@CsvBindByName(column = "Bandeira")
	@CsvBindByPosition(position = 10)
	private String bandeira;
	
	
	public Vendas() {
		
	}
	public Vendas(Integer id, String regiaoSigla, String stadoSigla, String municipio, String revenda, String revendaCNPJ, String produto, String dataColeta,
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public String toString(){
		return  regiaoSigla+estadoSigla+municipio+revenda+cnpjdaRevenda+produto+datadaColeta+valordeVenda+valordeCompra+unidadedeMedida+bandeira;
	}
}
