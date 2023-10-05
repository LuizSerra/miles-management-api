package com.netmaxi.mm.api.user.dto;

import java.util.List;

import com.netmaxi.mm.api.program.Program;
import com.netmaxi.mm.api.role.Role;
import com.netmaxi.mm.api.user.User;
/*This DTO will hide the Password information */
public record UserResponseDTO(Long id, Boolean active, String name, String email, List<Role> roles, List<Program> programs) {
	public UserResponseDTO(User user) {
		this(user.getId(), user.getActive(), user.getName(), user.getEmail(), user.getRoles(), user.getPrograms());
	}
}
