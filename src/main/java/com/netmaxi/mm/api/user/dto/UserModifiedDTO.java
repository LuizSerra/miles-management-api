package com.netmaxi.mm.api.user.dto;

import com.netmaxi.mm.api.user.User;
/*This DTO will hide the Password information */
public record UserModifiedDTO(Long id, Boolean active, String name, String email) {
	public UserModifiedDTO(User user) {
		this(user.getId(), user.getActive(), user.getName(), user.getEmail());
	}
}
