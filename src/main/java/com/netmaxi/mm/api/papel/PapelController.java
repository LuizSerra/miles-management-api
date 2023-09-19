package com.netmaxi.mm.api.papel;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.netmaxi.mm.api.papel.dto.PapelAtualizadoDTO;
import com.netmaxi.mm.api.papel.dto.PapelEntradaDTO;
import com.netmaxi.mm.api.papel.dto.PapelRetornoDTO;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/papeis")
public class PapelController {
	
	private PapelRepository papelRepository;
	
	public PapelController(PapelRepository papelRepository) {
		this.papelRepository = papelRepository;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<PapelAtualizadoDTO> criar(@RequestBody @Valid PapelEntradaDTO papelDTO, UriComponentsBuilder uriBuilder) {
		Papel papel = new Papel(papelDTO);
		papelRepository.save(papel);
		URI uri = uriBuilder.path("/papeis/{id}").buildAndExpand(papel.getId()).toUri();
		return ResponseEntity.created(uri).body(new PapelAtualizadoDTO(papel));
	}
	
	@GetMapping
	public ResponseEntity<Page<PapelRetornoDTO>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pag){
		var page = papelRepository.findAll(pag).map(PapelRetornoDTO::new);
		return ResponseEntity.ok(page);
	}

}
