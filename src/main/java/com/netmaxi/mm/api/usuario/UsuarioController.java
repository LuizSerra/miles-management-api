package com.netmaxi.mm.api.usuario;

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
import com.netmaxi.mm.api.usuario.service.UsuarioService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	private final UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<UsuarioAtualizadoDTO> criar(@RequestBody @Valid UsuarioEntradaDTO usuarioDTO, UriComponentsBuilder uriBuilder) {
		return usuarioService.criar(usuarioDTO, uriBuilder);
	}
	
	@GetMapping
	public ResponseEntity<Page<UsuarioRetornoDTO>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pag){
		return usuarioService.listar(pag);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioAtualizadoDTO> buscarPorId(@PathVariable Long id){
		return usuarioService.buscarPorId(id);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<UsuarioAtualizadoDTO> atualizar(@RequestBody @Valid UsuarioAtualizarDTO usuarioDTO) {
		return usuarioService.atualizar(usuarioDTO);
	}

}
