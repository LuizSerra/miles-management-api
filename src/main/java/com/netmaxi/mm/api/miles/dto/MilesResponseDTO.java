package com.netmaxi.mm.api.miles.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.netmaxi.mm.api.miles.Miles;
import com.netmaxi.mm.api.program.Program;

public record MilesResponseDTO(Long id, Integer amount, BigDecimal price, LocalDate expiration, Program program) {
	public MilesResponseDTO(Miles miles) {
		this(miles.getId(), miles.getAmount(), miles.getPrice(),  miles.getExpiration(), miles.getProgram());
	}
}
