package com.netmaxi.mm.api.usuario.service;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import com.netmaxi.mm.api.usuario.Usuario;
import com.netmaxi.mm.api.usuario.UsuarioRepository;
import com.netmaxi.mm.api.usuario.dto.UsuarioAtualizadoDTO;
import com.netmaxi.mm.api.usuario.dto.UsuarioAtualizarDTO;
import com.netmaxi.mm.api.usuario.dto.UsuarioEntradaDTO;
import com.netmaxi.mm.api.usuario.dto.UsuarioRetornoDTO;

public class UsuarioServiceImpl implements UsuarioService {

	private final UsuarioRepository usuarioRepository;

	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public ResponseEntity<UsuarioAtualizadoDTO> criar(UsuarioEntradaDTO usuarioDTO, UriComponentsBuilder uriBuilder) {
		Usuario usuario = new Usuario(usuarioDTO);
		usuarioRepository.save(usuario);
		URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(new UsuarioAtualizadoDTO(usuario));
	}

	@Override
	public ResponseEntity<Page<UsuarioRetornoDTO>> listar(Pageable pag) {
		var page = usuarioRepository.findAll(pag).map(UsuarioRetornoDTO::new);
		return ResponseEntity.ok(page);
	}

	@Override
	public ResponseEntity<UsuarioAtualizadoDTO> buscarPorId(Long id) {
		var usuario = usuarioRepository.getReferenceById(id);
		return ResponseEntity.ok(new UsuarioAtualizadoDTO(usuario));
	}

	@Override
	public ResponseEntity<UsuarioAtualizadoDTO> atualizar(UsuarioAtualizarDTO usuarioDTO) {
		var usuario = usuarioRepository.getReferenceById(usuarioDTO.id());
		usuario.atualiza(usuarioDTO);
		return ResponseEntity.ok(new UsuarioAtualizadoDTO(usuario));
	}

}
