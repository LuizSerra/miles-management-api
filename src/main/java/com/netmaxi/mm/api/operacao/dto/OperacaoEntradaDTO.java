package com.netmaxi.mm.api.operacao.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.netmaxi.mm.api.operacao.Tipo;
import com.netmaxi.mm.api.programa.Programa;

public record OperacaoEntradaDTO(
		LocalDate data, 
		Tipo tipo, 
		Integer quantidade,
		BigDecimal valor,
		Programa origem, 
		Programa destino) 
{}
