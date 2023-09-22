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

import com.netmaxi.mm.api.operacao.dto.OperacaoEntradaDTO;
import com.netmaxi.mm.api.operacao.dto.OperacaoRetornoDTO;
import com.netmaxi.mm.api.operacao.service.OperacaoService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/operacoes")
public class OperacaoController {

	private final OperacaoService operacaoService;
	
	public OperacaoController(OperacaoService operacaoService) {
		this.operacaoService = operacaoService;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<OperacaoRetornoDTO> criar(@RequestBody @Valid OperacaoEntradaDTO operacaoDTO, UriComponentsBuilder uriBuilder) {
		var operacaoCriada = operacaoService.criar(operacaoDTO);
		URI uri = uriBuilder.path("/operacoes/{id}").buildAndExpand(operacaoCriada.getId()).toUri();
		return ResponseEntity.created(uri).body(new OperacaoRetornoDTO(operacaoCriada));
	}
	
	@GetMapping
	public ResponseEntity<Page<OperacaoRetornoDTO>> listar(@PageableDefault(size = 10, sort = {"data"}) Pageable pag){
		var page =  operacaoService.listar(pag);
		return ResponseEntity.ok(page.map(OperacaoRetornoDTO::new));
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<OperacaoRetornoDTO> buscarPorId(@PathVariable Long id){
		var operacaoEncontrada = operacaoService.buscarPorId(id);
		return ResponseEntity.ok(new OperacaoRetornoDTO(operacaoEncontrada));
	}
	
}
