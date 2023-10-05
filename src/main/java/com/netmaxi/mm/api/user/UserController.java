package com.netmaxi.mm.api.user;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.netmaxi.mm.api.user.dto.UserModifiedDTO;
import com.netmaxi.mm.api.user.dto.UserModifyDTO;
import com.netmaxi.mm.api.user.dto.UserRequestDTO;
import com.netmaxi.mm.api.user.dto.UserResponseDTO;
import com.netmaxi.mm.api.user.service.UserService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private final UserService usuarioService;

	public UserController(UserService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<UserResponseDTO> create(@RequestBody @Valid UserRequestDTO usuarioDTO, UriComponentsBuilder uriBuilder) {
		User userCreated =  usuarioService.create(usuarioDTO);
		URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(userCreated.getId()).toUri();
		return ResponseEntity.created(uri).body(new UserResponseDTO(userCreated));
	}
	
	@GetMapping
	public ResponseEntity<Page<UserResponseDTO>> list(@PageableDefault(size = 10) Pageable pag){
	    Page<UserResponseDTO> page = usuarioService.list(pag);
	    return ResponseEntity.ok(page);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) throws Exception{
		var usuario = usuarioService.findByID(id);
		return ResponseEntity.ok(new UserResponseDTO(usuario));
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<UserModifiedDTO> atualizar(@RequestBody @Valid UserModifyDTO usuarioDTO) {
		User usuarioAtualizado = usuarioService.update(usuarioDTO);
		return ResponseEntity.ok(new UserModifiedDTO(usuarioAtualizado));
	}

}
