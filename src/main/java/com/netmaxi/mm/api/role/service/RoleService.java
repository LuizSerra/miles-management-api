package com.netmaxi.mm.api.role.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.netmaxi.mm.api.role.Role;
import com.netmaxi.mm.api.role.dto.RoleRequestDTO;

public interface RoleService {
	
	public Role criar(RoleRequestDTO roleDTO);
	
	public Page<Role> list(Pageable pag);

}
