package com.netmaxi.mm.api.usuario.dto;

import com.netmaxi.mm.api.usuario.Usuario;

public record UsuarioRetornoDTO(Long id, Boolean ativo, String nome, String email, String senha) {
	public UsuarioRetornoDTO(Usuario usuario) {
		this(usuario.getId(), usuario.isAtivo(), usuario.getNome(), usuario.getEmail(), usuario.getSenha());
	}
}
