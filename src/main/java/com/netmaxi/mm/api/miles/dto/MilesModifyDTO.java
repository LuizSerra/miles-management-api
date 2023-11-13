package com.netmaxi.mm.api.miles.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.netmaxi.mm.api.program.Program;

public record MilesModifyDTO(Long id, Integer amount, BigDecimal price, LocalDate expiration, Program program) {

}
