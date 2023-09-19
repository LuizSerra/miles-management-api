package com.netmaxi.mm.api.usuario.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.UriComponentsBuilder;

import com.netmaxi.mm.api.usuario.dto.UsuarioAtualizadoDTO;
import com.netmaxi.mm.api.usuario.dto.UsuarioAtualizarDTO;
import com.netmaxi.mm.api.usuario.dto.UsuarioEntradaDTO;
import com.netmaxi.mm.api.usuario.dto.UsuarioRetornoDTO;

public interface UsuarioService {
	
	public ResponseEntity<UsuarioAtualizadoDTO> criar(UsuarioEntradaDTO usuarioDTO, UriComponentsBuilder uriBuilder);
	
	public ResponseEntity<Page<UsuarioRetornoDTO>> listar(Pageable pag);
	
	public ResponseEntity<UsuarioAtualizadoDTO> buscarPorId(@PathVariable Long id);
	
	public ResponseEntity<UsuarioAtualizadoDTO> atualizar(UsuarioAtualizarDTO usuarioDTO);

}
