package com.netmaxi.mm.api.operacao.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import com.netmaxi.mm.api.operacao.dto.OperacaoAtualizadaDTO;
import com.netmaxi.mm.api.operacao.dto.OperacaoEntradaDTO;
import com.netmaxi.mm.api.operacao.dto.OperacaoRetornoDTO;

public interface OperacaoService {
	
	public ResponseEntity<OperacaoAtualizadaDTO> criar(OperacaoEntradaDTO operacaoDTO, UriComponentsBuilder uriBuilder);
	
	public ResponseEntity<Page<OperacaoRetornoDTO>> listar(Pageable pag);
	
	public ResponseEntity<OperacaoAtualizadaDTO> buscarPorId(Long id);

}
