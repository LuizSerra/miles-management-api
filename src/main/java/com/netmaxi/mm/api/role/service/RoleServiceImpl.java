package com.netmaxi.mm.api.role.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.netmaxi.mm.api.error.ValidationException;
import com.netmaxi.mm.api.role.Role;
import com.netmaxi.mm.api.role.RoleRepository;
import com.netmaxi.mm.api.role.dto.RoleRequestDTO;
import com.netmaxi.mm.api.role.dto.RoleResponseDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class RoleServiceImpl implements RoleService {
	
	private final RoleRepository roleRepository;
	
	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public RoleResponseDTO create(RoleRequestDTO roleDTO) {
		log.info("CREATING ROLE:::START");
		if (findByName(roleDTO.name()) != null) throw new ValidationException("A role with this name is already created.");
		Role role = new Role(roleDTO);
		roleRepository.save(role);
		log.info("CREATING ROLE:::END");
		return new RoleResponseDTO(role);
	}

	@Override
	public Page<RoleResponseDTO> list(Pageable pag) {
		log.info("LISTING ROLES:::START");
		Page<Role> page = roleRepository.findAll(pag);
		log.info("LISTING ROLES:::END");
		return page.map(RoleResponseDTO::new);
	}
	
	@Override
	public RoleResponseDTO findByName(String name) {
		log.info("FINDING ROLE BY NAME:::START");
		Role role =	roleRepository.findByNameIgnoreCase(name);
		log.info("FINDING ROLE BY NAME:::END::: ROLE FOUND: {}", role);
		return role != null ? new RoleResponseDTO(role) : null;
	}
	
}
