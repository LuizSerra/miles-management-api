package com.netmaxi.mm.api.role.dto;

import jakarta.validation.constraints.NotBlank;

public record RoleRequestDTO(
		@NotBlank
		String name) {
}
