package com.netmaxi.mm.api.role.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.netmaxi.mm.api.role.dto.RoleRequestDTO;
import com.netmaxi.mm.api.role.dto.RoleResponseDTO;

public interface RoleService {
	
	public RoleResponseDTO create(RoleRequestDTO roleDTO);
	
	public Page<RoleResponseDTO> list(Pageable pag);
	
	public RoleResponseDTO findByName(String name);

}
