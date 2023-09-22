package com.netmaxi.mm.api.operacao.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.netmaxi.mm.api.operacao.Operacao;
import com.netmaxi.mm.api.operacao.dto.OperacaoEntradaDTO;

public interface OperacaoService {
	
	public Operacao criar(OperacaoEntradaDTO operacaoDTO);
	
	public Page<Operacao> listar(Pageable pag);
	
	public Operacao buscarPorId(Long id);

}
