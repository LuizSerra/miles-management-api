package com.netmaxi.mm.api.role;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.netmaxi.mm.api.role.dto.RoleRequestDTO;
import com.netmaxi.mm.api.role.dto.RoleResponseDTO;
import com.netmaxi.mm.api.role.service.RoleService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/roles")
public class RoleController {
	
	private RoleService roleService;
	
	public RoleController(RoleService roleService) {
		this.roleService =  roleService;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<RoleResponseDTO> create(@RequestBody @Valid RoleRequestDTO roleRequestDTO, UriComponentsBuilder uriBuilder) {
		var createdRole = roleService.create(roleRequestDTO);
		URI uri = uriBuilder.path("/roles/{id}").buildAndExpand(createdRole.id()).toUri();
		return ResponseEntity.created(uri).body(createdRole);
	}
	
	@GetMapping
	public ResponseEntity<Page<RoleResponseDTO>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pag){
		Page<RoleResponseDTO> page =  roleService.list(pag);
		return ResponseEntity.ok(page);
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<RoleResponseDTO> findByName(@PathVariable String name) {
		var found = roleService.findByName(name);
		return found != null ? ResponseEntity.ok(found) : ResponseEntity.notFound().build();
	}

}
