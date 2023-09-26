package com.netmaxi.mm.api.usuario.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.netmaxi.mm.api.papel.Papel;
import com.netmaxi.mm.api.papel.PapelRepository;
import com.netmaxi.mm.api.usuario.Usuario;
import com.netmaxi.mm.api.usuario.UsuarioRepository;
import com.netmaxi.mm.api.usuario.dto.UsuarioAtualizarDTO;
import com.netmaxi.mm.api.usuario.dto.UsuarioEntradaDTO;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final PapelRepository papelRepository;

	public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PapelRepository papelRepository) {
		this.usuarioRepository = usuarioRepository;
		this.papelRepository = papelRepository;
	}

	@Override
	public Usuario criar(UsuarioEntradaDTO usuarioDTO) {
		Usuario usuario = new Usuario(usuarioDTO);
		
		List<Papel> papeis = papelRepository.findAllById(usuarioDTO.papeis()); 
		usuario.setPapeis(papeis);
		usuario.setAtivo(true);
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
