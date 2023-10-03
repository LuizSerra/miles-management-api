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

}
