package com.netmaxi.mm.api.operacao.service;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.netmaxi.mm.api.operacao.Operacao;
import com.netmaxi.mm.api.operacao.OperacaoRepository;
import com.netmaxi.mm.api.operacao.dto.OperacaoAtualizadaDTO;
import com.netmaxi.mm.api.operacao.dto.OperacaoEntradaDTO;
import com.netmaxi.mm.api.operacao.dto.OperacaoRetornoDTO;

@Service
public class OperacaoServiceImpl implements OperacaoService {
	
	private final OperacaoRepository operacaoRepository;
	
	public OperacaoServiceImpl(OperacaoRepository operacaoRepository) {
		this.operacaoRepository = operacaoRepository;
	}

	@Override
	public ResponseEntity<OperacaoAtualizadaDTO> criar(OperacaoEntradaDTO operacaoDTO, UriComponentsBuilder uriBuilder) {
		Operacao operacao = new Operacao(operacaoDTO);
		operacaoRepository.save(operacao);
		URI uri = uriBuilder.path("/operacoes/{id}").buildAndExpand(operacao.getId()).toUri();
		return ResponseEntity.created(uri).body(new OperacaoAtualizadaDTO(operacao));
	}

	@Override
	public ResponseEntity<Page<OperacaoRetornoDTO>> listar(Pageable pag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<OperacaoAtualizadaDTO> buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
