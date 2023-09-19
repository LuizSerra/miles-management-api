package com.netmaxi.mm.api.operacao.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.netmaxi.mm.api.operacao.Operacao;
import com.netmaxi.mm.api.operacao.Tipo;

public record OperacaoRetornoDTO(Long id, LocalDate data, Tipo tipo, Integer quantidade, BigDecimal valor) {
	public OperacaoRetornoDTO(Operacao operacao) {
		this(operacao.getId(), operacao.getData(), operacao.getTipo(), operacao.getQuantidade(), operacao.getValor());
	}
}
