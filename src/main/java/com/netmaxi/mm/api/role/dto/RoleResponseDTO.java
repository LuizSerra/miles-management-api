package com.netmaxi.mm.api.role.dto;

import com.netmaxi.mm.api.role.Role;

public record RoleResponseDTO(Long id, String name) {
	public RoleResponseDTO(Role role) {
		this(role.getId(), role.getName());
	}
}
