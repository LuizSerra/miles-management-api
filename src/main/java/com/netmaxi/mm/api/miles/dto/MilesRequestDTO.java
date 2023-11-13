package com.netmaxi.mm.api.miles.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.netmaxi.mm.api.miles.Miles;

import jakarta.validation.constraints.NotBlank;

public record MilesRequestDTO(
		@NotBlank
		Integer amount,
		@NotBlank
		BigDecimal price,
		@NotBlank
		LocalDate expiration, 
		@NotBlank
		Long program) {

	public MilesRequestDTO(Miles miles) {
		this(miles.getAmount(), miles.getPrice(), miles.getExpiration(), miles.getProgram().getId());
	}

}
