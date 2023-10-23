package com.netmaxi.mm.api.program.dto;

import jakarta.validation.constraints.NotNull;

public record ProgramModifyDTO(
		@NotNull
		Long id, 
		String name, 
		String description,
		Integer balance,
		Integer balanceAvailable
		
		) {
	public ProgramModifyDTO(ProgramResponseDTO prog) {
		this(prog.id(), prog.name(), prog.description(), prog.balance(), prog.balanceAvailable());
	}
	
}
