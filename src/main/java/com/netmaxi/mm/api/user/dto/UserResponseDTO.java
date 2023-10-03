package com.netmaxi.mm.api.user.dto;

import com.netmaxi.mm.api.user.User;
/*This DTO will hide the Password information */
public record UserResponseDTO(Boolean active, String name, String email) {
	public UserResponseDTO(User user) {
		this(user.getActive(), user.getName(), user.getEmail());
	}
}
