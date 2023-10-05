package com.netmaxi.mm.api.user.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

import com.netmaxi.mm.api.user.User;
import com.netmaxi.mm.api.user.dto.UserModifyDTO;
import com.netmaxi.mm.api.user.dto.UserRequestDTO;
import com.netmaxi.mm.api.user.dto.UserResponseDTO;

public interface UserService {
	
	public User create(UserRequestDTO userRequestDTO);
	
	public Page<UserResponseDTO> list(Pageable pag);
	
	public User findByID(@PathVariable Long id);
	
	public User update(UserModifyDTO userModifyDTO);

}
