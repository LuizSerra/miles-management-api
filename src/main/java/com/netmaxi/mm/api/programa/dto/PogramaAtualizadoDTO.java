package com.netmaxi.mm.api.programa.dto;

import com.netmaxi.mm.api.programa.Programa;

public record PogramaAtualizadoDTO(Long id, String nome, String descricao) {
	public PogramaAtualizadoDTO(Programa prog) {
		this(prog.getId(), prog.getNome(), prog.getDescricao());
	}
}
