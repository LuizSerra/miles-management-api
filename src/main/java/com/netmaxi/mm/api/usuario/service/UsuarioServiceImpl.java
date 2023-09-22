package com.netmaxi.mm.api.usuario.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.netmaxi.mm.api.usuario.Usuario;
import com.netmaxi.mm.api.usuario.UsuarioRepository;
import com.netmaxi.mm.api.usuario.dto.UsuarioAtualizarDTO;
import com.netmaxi.mm.api.usuario.dto.UsuarioEntradaDTO;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private final UsuarioRepository usuarioRepository;

	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public Usuario criar(UsuarioEntradaDTO usuarioDTO) {
		Usuario usuario = new Usuario(usuarioDTO);
		usuario = usuarioRepository.save(usuario);
		return usuario;
	}

	@Override
	public Page<Usuario> listar(Pageable pag) {
		var page = usuarioRepository.findAll(pag);
		return page;
	}

	@Override
	public Usuario buscarPorId(Long id) {
		var usuarioEncontrado = usuarioRepository.getReferenceById(id);
		return usuarioEncontrado;
	}

	@Override
	public Usuario atualizar(UsuarioAtualizarDTO usuarioDTO) {
		var usuarioEncontrado = buscarPorId(usuarioDTO.id());
		usuarioEncontrado.atualiza(usuarioDTO);
		return usuarioEncontrado;
	}

}
