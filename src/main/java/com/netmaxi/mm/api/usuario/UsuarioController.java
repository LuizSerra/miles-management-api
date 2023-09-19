package com.netmaxi.mm.api.usuario;

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

import com.netmaxi.mm.api.usuario.dto.UsuarioAtualizadoDTO;
import com.netmaxi.mm.api.usuario.dto.UsuarioAtualizarDTO;
import com.netmaxi.mm.api.usuario.dto.UsuarioEntradaDTO;
import com.netmaxi.mm.api.usuario.dto.UsuarioRetornoDTO;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	UsuarioRepository usuarioRepository;

	public UsuarioController(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<UsuarioAtualizadoDTO> criar(@RequestBody @Valid UsuarioEntradaDTO usuarioDTO, UriComponentsBuilder uriBuilder) {
		Usuario usuario = new Usuario(usuarioDTO);
		usuarioRepository.save(usuario);
		URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(new UsuarioAtualizadoDTO(usuario));
	}
	
	@GetMapping
	public ResponseEntity<Page<UsuarioRetornoDTO>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pag){
		var page = usuarioRepository.findAll(pag).map(UsuarioRetornoDTO::new);
		return ResponseEntity.ok(page);
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioAtualizadoDTO> buscarPorId(@PathVariable Long id){
		var usuario = usuarioRepository.getReferenceById(id);
		return ResponseEntity.ok(new UsuarioAtualizadoDTO(usuario));
	}
	
	
	@PutMapping
	@Transactional
	public ResponseEntity<UsuarioAtualizadoDTO> atualizar(@RequestBody @Valid UsuarioAtualizarDTO usuarioDTO) {
		var usuario = usuarioRepository.getReferenceById(usuarioDTO.id());
			usuario.atualiza(usuarioDTO);
		return ResponseEntity.ok(new UsuarioAtualizadoDTO(usuario));
		
	}
	

}
