package com.netmaxi.mm.api.programa.dto;

import jakarta.validation.constraints.NotBlank;

public record ProgramaEntradaDTO (
		@NotBlank
		String nome, 
		
		String descricao,
		
		Integer saldo,
		
		Integer saldoDisponivel
		) {}
