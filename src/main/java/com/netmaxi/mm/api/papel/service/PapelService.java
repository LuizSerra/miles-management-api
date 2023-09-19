package com.netmaxi.mm.api.papel.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import com.netmaxi.mm.api.papel.dto.PapelAtualizadoDTO;
import com.netmaxi.mm.api.papel.dto.PapelEntradaDTO;
import com.netmaxi.mm.api.papel.dto.PapelRetornoDTO;

public interface PapelService {
	
	public ResponseEntity<PapelAtualizadoDTO> criar(PapelEntradaDTO papelDTO, UriComponentsBuilder uriBuilder);
	
	public ResponseEntity<Page<PapelRetornoDTO>> listar(Pageable pag);

}
