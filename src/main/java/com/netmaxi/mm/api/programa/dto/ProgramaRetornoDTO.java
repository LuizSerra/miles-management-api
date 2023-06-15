package com.netmaxi.mm.api.programa.dto;

import com.netmaxi.mm.api.programa.Programa;

public record ProgramaRetornoDTO(Long id, String nome, String descricao) {
	
	public ProgramaRetornoDTO(Programa programa) {
		this(programa.getId(), programa.getNome(), programa.getDescricao());
	}

}
