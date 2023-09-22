package com.netmaxi.mm.api.operacao.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.netmaxi.mm.api.operacao.Operacao;
import com.netmaxi.mm.api.operacao.OperacaoRepository;
import com.netmaxi.mm.api.operacao.dto.OperacaoEntradaDTO;

@Service
public class OperacaoServiceImpl implements OperacaoService {
	
	private final OperacaoRepository operacaoRepository;
	
	public OperacaoServiceImpl(OperacaoRepository operacaoRepository) {
		this.operacaoRepository = operacaoRepository;
	}

	@Override
	public Operacao criar(OperacaoEntradaDTO operacaoDTO) {
		Operacao operacaoCriada = new Operacao(operacaoDTO);
		operacaoRepository.save(operacaoCriada);
		return operacaoCriada;
	}

	@Override
	public Page<Operacao> listar(Pageable pag) {
		var page = operacaoRepository.findAll(pag);
		return page;
	}

	@Override
	public Operacao buscarPorId(Long id) {
		var operacaoEncontrada = operacaoRepository.getReferenceById(id);
		return operacaoEncontrada;
	}

}
