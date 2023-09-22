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

import com.netmaxi.mm.api.papel.dto.PapelEntradaDTO;
import com.netmaxi.mm.api.papel.dto.PapelRetornoDTO;
import com.netmaxi.mm.api.papel.service.PapelService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/papeis")
public class PapelController {
	
	private PapelService papelService;
	
	public PapelController(PapelService papelService) {
		this.papelService=  papelService;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<PapelRetornoDTO> criar(@RequestBody @Valid PapelEntradaDTO papelDTO, UriComponentsBuilder uriBuilder) {
		Papel papelCriado = papelService.criar(papelDTO);
		URI uri = uriBuilder.path("/papeis/{id}").buildAndExpand(papelCriado.getId()).toUri();
		return ResponseEntity.created(uri).body(new PapelRetornoDTO(papelCriado));
	}
	
	@GetMapping
	public ResponseEntity<Page<PapelRetornoDTO>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pag){
		Page<Papel> pageRetornada =  papelService.listar(pag);
		return ResponseEntity.ok(pageRetornada.map(PapelRetornoDTO::new));
	}

}
