package com.netmaxi.mm.api.usuario.dto;

import java.util.List;

public record UsuarioEntradaDTO(Boolean ativo, String nome, String email, String senha, List<Long> papeis) {
	
}
