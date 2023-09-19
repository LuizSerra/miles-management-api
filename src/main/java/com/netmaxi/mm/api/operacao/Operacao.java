package com.netmaxi.mm.api.operacao;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.netmaxi.mm.api.operacao.dto.OperacaoEntradaDTO;
import com.netmaxi.mm.api.programa.Programa;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity(name = "Operacao")
@Table(name = "operacoes")
public class Operacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.DATE)
	private LocalDate data;

	@Enumerated(EnumType.STRING)
	private Tipo tipo;

	private Integer quantidade;

	private BigDecimal valor;
	
	@ManyToOne
	@JoinColumn(name = "programa_origem_id")
	private Programa programaOrigem;

	@ManyToOne
	@JoinColumn(name = "programa_destino_id")
	private Programa programaDestino;

	public Operacao() {
		super();
	}

	public Operacao(OperacaoEntradaDTO operacaoDTO) {
		this.data = operacaoDTO.data();
		this.tipo  = operacaoDTO.tipo();
		this.quantidade = operacaoDTO.quantidade();
		this.valor = operacaoDTO.valor();
		this.programaOrigem = operacaoDTO.origem();
		this.programaDestino = operacaoDTO.destino();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Programa getProgramaOrigem() {
		return programaOrigem;
	}

	public void setProgramaOrigem(Programa programaOrigem) {
		this.programaOrigem = programaOrigem;
	}

	public Programa getProgramaDestino() {
		return programaDestino;
	}

	public void setProgramaDestino(Programa programaDestino) {
		this.programaDestino = programaDestino;
	}

}
