package com.netmaxi.mm.api.programa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import com.netmaxi.mm.api.programa.dto.ProgramaAtualizadoDTO;
import com.netmaxi.mm.api.programa.dto.ProgramaAtualizarDTO;
import com.netmaxi.mm.api.programa.dto.ProgramaEntradaDTO;
import com.netmaxi.mm.api.programa.dto.ProgramaRetornoDTO;

public interface ProgramaService {
	
	public ResponseEntity<ProgramaAtualizadoDTO> criar(ProgramaEntradaDTO programaDTO, UriComponentsBuilder uriBuilder);
	
	public ResponseEntity<Page<ProgramaRetornoDTO>> listar(Pageable pag);
	
	public ResponseEntity<ProgramaAtualizadoDTO> buscarPorId(Long id);
	
	public ResponseEntity<ProgramaAtualizadoDTO> atualizar(ProgramaAtualizarDTO programaDTO);

}
