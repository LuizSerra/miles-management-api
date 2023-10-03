package com.netmaxi.mm.api.program.dto;

import com.netmaxi.mm.api.program.Program;

public record ProgramModifiedDTO(Long id, String nome, String descricao, Integer saldo, Integer saldoDisponivel) {
	public ProgramModifiedDTO(Program prog) {
		this(prog.getId(), prog.getName(), prog.getDescription(), prog.getBalance(), prog.getBalanceAvailable());
	}
}
