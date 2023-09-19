package com.netmaxi.mm.api.programa.service;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.netmaxi.mm.api.programa.Programa;
import com.netmaxi.mm.api.programa.ProgramaRepository;
import com.netmaxi.mm.api.programa.dto.ProgramaAtualizadoDTO;
import com.netmaxi.mm.api.programa.dto.ProgramaAtualizarDTO;
import com.netmaxi.mm.api.programa.dto.ProgramaEntradaDTO;
import com.netmaxi.mm.api.programa.dto.ProgramaRetornoDTO;

@Service
public class ProgramaServiceImpl implements ProgramaService {
	
	private final ProgramaRepository programaRepository;
	
	public ProgramaServiceImpl(ProgramaRepository programaRepository) {
		this.programaRepository = programaRepository;
	}

	@Override
	public ResponseEntity<ProgramaAtualizadoDTO> criar(ProgramaEntradaDTO programaDTO, UriComponentsBuilder uriBuilder) {
		Programa programa = new Programa(programaDTO);
		programaRepository.save(programa);
		URI uri = uriBuilder.path("/programas/{id}").buildAndExpand(programa.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProgramaAtualizadoDTO(programa));
	}

	@Override
	public ResponseEntity<Page<ProgramaRetornoDTO>> listar(Pageable pag) {
		var page = programaRepository.findAll(pag).map(ProgramaRetornoDTO::new);
		return ResponseEntity.ok(page);
	}

	@Override
	public ResponseEntity<ProgramaAtualizadoDTO> buscarPorId(Long id) {
		var programa = programaRepository.getReferenceById(id);
		return ResponseEntity.ok(new ProgramaAtualizadoDTO(programa));
	}

	@Override
	public ResponseEntity<ProgramaAtualizadoDTO> atualizar(ProgramaAtualizarDTO programaDTO) {
		var programa = programaRepository.getReferenceById(programaDTO.id());
		programa.atualiza(programaDTO);
		return ResponseEntity.ok(new ProgramaAtualizadoDTO(programa));
	}

}
