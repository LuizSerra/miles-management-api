package com.netmaxi.mm.api.user.dto;

import java.util.List;

public record UserRequestDTO(Boolean active, String name, String email, String password, List<Long> roles) {
	
}
