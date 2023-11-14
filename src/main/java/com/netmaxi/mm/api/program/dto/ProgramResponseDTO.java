package com.netmaxi.mm.api.program.dto;

import java.util.List;

import com.netmaxi.mm.api.miles.Miles;
import com.netmaxi.mm.api.program.Program;

public record ProgramResponseDTO(Long id, String name, String description, Integer balance, Integer balanceAvailable, List<Miles> miles) {
	
	public ProgramResponseDTO(Program program) {
		this(program.getId() ,program.getName(), program.getDescription(), program.getBalance(), program.getBalanceAvailable(), program.getMiles());
	}

}
