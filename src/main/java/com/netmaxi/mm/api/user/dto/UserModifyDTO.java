package com.netmaxi.mm.api.user.dto;

import jakarta.validation.constraints.NotBlank;

public record UserModifyDTO(
		@NotBlank
		Long id, 
		Boolean active, 
		String name, 
		String email, 
		String password) 
{}
