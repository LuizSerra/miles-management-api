package com.netmaxi.mm.api.role.dto;

import com.netmaxi.mm.api.role.Role;

public record RoleUpdatedDTO(Long id, String name) {
	public RoleUpdatedDTO(Role role) {
		this(role.getId(), role.getName());
	}
}
