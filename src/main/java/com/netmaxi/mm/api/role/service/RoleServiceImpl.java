package com.netmaxi.mm.api.role.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.netmaxi.mm.api.role.Role;
import com.netmaxi.mm.api.role.RoleRepository;
import com.netmaxi.mm.api.role.dto.RoleRequestDTO;

@Service
public class RoleServiceImpl implements RoleService {
	
	private final RoleRepository roleRepository;
	
	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public Role criar(RoleRequestDTO roleDTO) {
		Role role = new Role(roleDTO);
		roleRepository.save(role);
		return role;
	}

	@Override
	public Page<Role> list(Pageable pag) {
		Page<Role> page = roleRepository.findAll(pag);
		return page;
	}

}
