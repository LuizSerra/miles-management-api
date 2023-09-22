package com.netmaxi.mm.api.usuario.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

import com.netmaxi.mm.api.usuario.Usuario;
import com.netmaxi.mm.api.usuario.dto.UsuarioAtualizarDTO;
import com.netmaxi.mm.api.usuario.dto.UsuarioEntradaDTO;

public interface UsuarioService {
	
	public Usuario criar(UsuarioEntradaDTO usuarioDTO);
	
	public Page<Usuario> listar(Pageable pag);
	
	public Usuario buscarPorId(@PathVariable Long id);
	
	public Usuario atualizar(UsuarioAtualizarDTO usuarioDTO);

}
