package com.netmaxi.mm.api.user.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.netmaxi.mm.api.role.Role;
import com.netmaxi.mm.api.role.RoleRepository;
import com.netmaxi.mm.api.user.User;
import com.netmaxi.mm.api.user.UsuarioRepository;
import com.netmaxi.mm.api.user.dto.UserModifyDTO;
import com.netmaxi.mm.api.user.dto.UserRequestDTO;
import com.netmaxi.mm.api.user.dto.UserResponseDTO;

@Service
public class UserServiceImpl implements UserService {

	private final UsuarioRepository usuarioRepository;
	private final RoleRepository papelRepository;

	public UserServiceImpl(UsuarioRepository usuarioRepository, RoleRepository papelRepository) {
		this.usuarioRepository = usuarioRepository;
		this.papelRepository = papelRepository;
	}

	@Override
	public User create(UserRequestDTO userRequestDTO) {
		User user = new User(userRequestDTO);
		List<Role> roles = papelRepository.findAllById(userRequestDTO.roles()); 
		user.setRoles(roles);
		user.setActive(true);
		user = usuarioRepository.save(user);
		return user;
	}

	@Override
	public Page<UserResponseDTO> list(Pageable pag) {
		var page = usuarioRepository.findAll(pag).map(UserResponseDTO::new);
		return page;
	}

	@Override
	public User findByID(Long id) {
		var userFound = usuarioRepository.getReferenceById(id);
		return userFound;
	}

	@Override
	public User update(UserModifyDTO usuarioDTO) {
		var UserFound = findByID(usuarioDTO.id());
		UserFound.update(usuarioDTO);
		return UserFound;
	}

}
