package com.netmaxi.mm.api.programa.dto;

import com.netmaxi.mm.api.programa.Programa;

public record ProgramaAtualizadoDTO(Long id, String nome, String descricao) {
	public ProgramaAtualizadoDTO(Programa prog) {
		this(prog.getId(), prog.getNome(), prog.getDescricao());
	}
}
