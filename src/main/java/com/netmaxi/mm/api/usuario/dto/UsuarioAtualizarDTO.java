package com.netmaxi.mm.api.usuario.dto;

public record UsuarioAtualizarDTO(
		Long id, 
		Boolean ativo, 
		String nome, 
		String email, 
		String senha) 
{}
