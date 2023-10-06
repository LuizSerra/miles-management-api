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
	public ResponseEntity<TransactionResponseDTO> create(@RequestBody @Valid TransactionRequestDTO transactionDTO, UriComponentsBuilder uriBuilder) {
		var transactionCreated = operacaoService.create(transactionDTO);
		URI uri = uriBuilder.path("/operacoes/{id}").buildAndExpand(transactionCreated.id()).toUri();
		return ResponseEntity.created(uri).body(transactionCreated);
	}
	
	@GetMapping
	public ResponseEntity<Page<TransactionResponseDTO>> list(@PageableDefault(size = 10, sort = {"data"}) Pageable pag){
		var page =  operacaoService.list(pag);
		return ResponseEntity.ok(page);
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<TransactionResponseDTO> findById(@PathVariable Long id){
		var operacaoEncontrada = operacaoService.findByID(id);
		return ResponseEntity.ok(operacaoEncontrada);
	}
	
}
