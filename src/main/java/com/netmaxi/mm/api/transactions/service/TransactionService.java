package com.netmaxi.mm.api.transactions.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.netmaxi.mm.api.transactions.dto.TransactionRequestDTO;
import com.netmaxi.mm.api.transactions.dto.TransactionResponseDTO;

public interface TransactionService {
	
	public TransactionResponseDTO create(TransactionRequestDTO transactionRequestDTO);
	
	public Page<TransactionResponseDTO> list(Pageable pag);
	
	public TransactionResponseDTO findByID(Long id);

}
