package com.netmaxi.mm.api.transactions;

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

import com.netmaxi.mm.api.transactions.dto.TransactionRequestDTO;
import com.netmaxi.mm.api.transactions.dto.TransactionResponseDTO;
import com.netmaxi.mm.api.transactions.service.TransactionService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	private final TransactionService operacaoService;
	
	public TransactionController(TransactionService operacaoService) {
		this.operacaoService = operacaoService;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<TransactionResponseDTO> create(@RequestBody @Valid TransactionRequestDTO operacaoDTO, UriComponentsBuilder uriBuilder) {
		var operacaoCriada = operacaoService.create(operacaoDTO);
		URI uri = uriBuilder.path("/operacoes/{id}").buildAndExpand(operacaoCriada.getId()).toUri();
		return ResponseEntity.created(uri).body(new TransactionResponseDTO(operacaoCriada));
	}
	
	@GetMapping
	public ResponseEntity<Page<TransactionResponseDTO>> list(@PageableDefault(size = 10, sort = {"data"}) Pageable pag){
		var page =  operacaoService.list(pag);
		return ResponseEntity.ok(page.map(TransactionResponseDTO::new));
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<TransactionResponseDTO> findById(@PathVariable Long id){
		var operacaoEncontrada = operacaoService.findByID(id);
		return ResponseEntity.ok(new TransactionResponseDTO(operacaoEncontrada));
	}
	
}
