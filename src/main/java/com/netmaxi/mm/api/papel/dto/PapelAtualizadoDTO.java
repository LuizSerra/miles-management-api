package com.netmaxi.mm.api.papel.dto;

import com.netmaxi.mm.api.papel.Papel;

public record PapelAtualizadoDTO(Long id, String name) {
	public PapelAtualizadoDTO(Papel papel) {
		this(papel.getId(), papel.getNome());
	}
}
