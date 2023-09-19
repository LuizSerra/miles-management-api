package com.netmaxi.mm.api.papel.dto;

import com.netmaxi.mm.api.papel.Papel;

public record PapelRetornoDTO(Long id, String name) {
	public PapelRetornoDTO(Papel papel) {
		this(papel.getId(), papel.getNome());
	}
}
