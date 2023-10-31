package com.netmaxi.mm.api.user.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.netmaxi.mm.api.error.ValidationException;
import com.netmaxi.mm.api.role.Role;
import com.netmaxi.mm.api.role.RoleRepository;
import com.netmaxi.mm.api.user.User;
import com.netmaxi.mm.api.user.UserRepository;
import com.netmaxi.mm.api.user.dto.UserModifiedDTO;
import com.netmaxi.mm.api.user.dto.UserModifyDTO;
import com.netmaxi.mm.api.user.dto.UserRequestDTO;
import com.netmaxi.mm.api.user.dto.UserResponseDTO;



@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;

	public UserServiceImpl(UserRepository usuarioRepository, RoleRepository papelRepository) {
		this.userRepository = usuarioRepository;
		this.roleRepository = papelRepository;
	}

	@Override
	public UserResponseDTO create(UserRequestDTO userRequestDTO) {
		if(userRepository.findByEmailIgnoreCase(userRequestDTO.email()) != null) throw new ValidationException("A user with this e-mail is already created");
		User user = new User(userRequestDTO);
		List<Role> roles = roleRepository.findAllById(userRequestDTO.roles()); 
		user.setRoles(roles);
		user.setActive(true);
		user = userRepository.save(user);
		return new UserResponseDTO(user);
	}

	@Override
	public Page<UserResponseDTO> list(Pageable pag) {
		var page = userRepository.findAll(pag).map(UserResponseDTO::new);
		return page;
	}

	@Override
	public UserResponseDTO findByID(Long id) {
		var userFound = userRepository.getReferenceById(id);
		return new UserResponseDTO(userFound);
	}

	@Override
	public UserModifiedDTO update(UserModifyDTO usuarioDTO) {
		var UserFound = userRepository.getReferenceById(usuarioDTO.id());
		UserFound.update(usuarioDTO);
		return new UserModifiedDTO(UserFound);
	}

}
