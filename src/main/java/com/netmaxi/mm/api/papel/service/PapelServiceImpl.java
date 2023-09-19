package com.netmaxi.mm.api.papel.service;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.netmaxi.mm.api.papel.Papel;
import com.netmaxi.mm.api.papel.PapelRepository;
import com.netmaxi.mm.api.papel.dto.PapelAtualizadoDTO;
import com.netmaxi.mm.api.papel.dto.PapelEntradaDTO;
import com.netmaxi.mm.api.papel.dto.PapelRetornoDTO;

@Service
public class PapelServiceImpl implements PapelService {
	
	private final PapelRepository papelRepository;
	
	public PapelServiceImpl(PapelRepository papelRepository) {
		this.papelRepository = papelRepository;
	}

	@Override
	public ResponseEntity<PapelAtualizadoDTO> criar(PapelEntradaDTO papelDTO, UriComponentsBuilder uriBuilder) {
		Papel papel = new Papel(papelDTO);
		papelRepository.save(papel);
		URI uri = uriBuilder.path("/papeis/{id}").buildAndExpand(papel.getId()).toUri();
		return ResponseEntity.created(uri).body(new PapelAtualizadoDTO(papel));
	}

	@Override
	public ResponseEntity<Page<PapelRetornoDTO>> listar(Pageable pag) {
		var page = papelRepository.findAll(pag).map(PapelRetornoDTO::new);
		return ResponseEntity.ok(page);
	}

}
