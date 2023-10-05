package com.netmaxi.mm.api.program.dto;

import jakarta.validation.constraints.NotBlank;

public record ProgramRequestDTO (
		@NotBlank
		String name, 
		
		String description,
		
		Integer balance,
		
		Integer balanceAvailable,
		
		Long user
		) {}
