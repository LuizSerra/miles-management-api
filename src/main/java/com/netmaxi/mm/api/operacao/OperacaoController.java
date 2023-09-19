package com.netmaxi.mm.api.operacao;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.netmaxi.mm.api.operacao.dto.OperacaoAtualizadaDTO;
import com.netmaxi.mm.api.operacao.dto.OperacaoEntradaDTO;
import com.netmaxi.mm.api.operacao.dto.OperacaoRetornoDTO;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/operacoes")
public class OperacaoController {

	private OperacaoRepository operacaoRepository;
	
	public OperacaoController(OperacaoRepository operacaoRepository) {
		this.operacaoRepository = operacaoRepository;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<OperacaoAtualizadaDTO> criar(@RequestBody @Valid OperacaoEntradaDTO operacaoDTO, UriComponentsBuilder uriBuilder) {
		Operacao operacao = new Operacao(operacaoDTO);
		operacaoRepository.save(operacao);
		URI uri = uriBuilder.path("/operacoes/{id}").buildAndExpand(operacao.getId()).toUri();
		return ResponseEntity.created(uri).body(new OperacaoAtualizadaDTO(operacao));
	}
	
	@GetMapping
	public ResponseEntity<Page<OperacaoRetornoDTO>> listar(@PageableDefault(size = 10, sort = {"data"}) Pageable pag){
		var page = operacaoRepository.findAll(pag).map(OperacaoRetornoDTO::new);
		return ResponseEntity.ok(page);
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<OperacaoAtualizadaDTO> buscarPorId(@PathVariable Long id){
		var operacao = operacaoRepository.getReferenceById(id);
		return ResponseEntity.ok(new OperacaoAtualizadaDTO(operacao));
	}
	
}
