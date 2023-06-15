package com.netmaxi.mm.api.programa.dto;

import jakarta.validation.constraints.NotNull;

public record ProgramaAtualizarDTO(
		@NotNull
		Long id, 
		String nome, 
		String descricao) {

}
