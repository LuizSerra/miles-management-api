package com.netmaxi.mm.api.role.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.netmaxi.mm.api.error.ValidationException;
import com.netmaxi.mm.api.role.Role;
import com.netmaxi.mm.api.role.RoleRepository;
import com.netmaxi.mm.api.role.dto.RoleRequestDTO;
import com.netmaxi.mm.api.role.dto.RoleResponseDTO;

@Service
public class RoleServiceImpl implements RoleService {
	
	private final RoleRepository roleRepository;
	
	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public RoleResponseDTO create(RoleRequestDTO roleDTO) {
		
		if (findByName(roleDTO.name()) != null) throw new ValidationException("A role with this name is already created.");
		
		Role role = new Role(roleDTO);
		roleRepository.save(role);
		return new RoleResponseDTO(role);
	}

	@Override
	public Page<RoleResponseDTO> list(Pageable pag) {
		Page<Role> page = roleRepository.findAll(pag);
		return page.map(RoleResponseDTO::new);
	}
	
	@Override
	public RoleResponseDTO findByName(String name) {
		Role role =	roleRepository.findByNameIgnoreCase(name);
		return role != null ? new RoleResponseDTO(role) : null;
	}
	
}
