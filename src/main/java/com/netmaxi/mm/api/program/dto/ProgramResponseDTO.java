package com.netmaxi.mm.api.program.dto;

import com.netmaxi.mm.api.program.Program;

public record ProgramResponseDTO(Long id, String name, String description, Integer balance, Integer balanceAvailable) {
	
	public ProgramResponseDTO(Program program) {
		this(program.getId() ,program.getName(), program.getDescription(), program.getBalance(), program.getBalanceAvailable());
	}

}
