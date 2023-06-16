package com.netmaxi.mm.api.programa;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.netmaxi.mm.api.programa.dto.PogramaAtualizadoDTO;
import com.netmaxi.mm.api.programa.dto.ProgramaAtualizarDTO;
import com.netmaxi.mm.api.programa.dto.ProgramaEntradaDTO;
import com.netmaxi.mm.api.programa.dto.ProgramaRetornoDTO;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/programas")
public class ProgramController {
	
	private ProgramaRepository programaRepository;
	
	public ProgramController(ProgramaRepository programaRepository) {
		this.programaRepository = programaRepository;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<PogramaAtualizadoDTO> criar(@RequestBody @Valid ProgramaEntradaDTO programaDTO, UriComponentsBuilder uriBuilder) {
		Programa programa = new Programa(programaDTO);
		programaRepository.save(programa);
		URI uri = uriBuilder.path("/programas/{id}").buildAndExpand(programa.getId()).toUri();
		return ResponseEntity.created(uri).body(new PogramaAtualizadoDTO(programa));
	}
	
	@GetMapping
	public ResponseEntity<Page<ProgramaRetornoDTO>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pag){
		var page = programaRepository.findAll(pag).map(ProgramaRetornoDTO::new);
		return ResponseEntity.ok(page);
	}

	
	@GetMapping("/{id}")
	public ResponseEntity buscarPorId(@PathVariable Long id){
		var programa = programaRepository.getReferenceById(id);
		return ResponseEntity.ok(new PogramaAtualizadoDTO(programa));
	}
	
	
	@PutMapping
	@Transactional
	public ResponseEntity<PogramaAtualizadoDTO> atualizar(@RequestBody @Valid ProgramaAtualizarDTO programaDTO) {
		var programa = programaRepository.getReferenceById(programaDTO.id());
		programa.atualiza(programaDTO);
		return ResponseEntity.ok(new PogramaAtualizadoDTO(programa));
		
	}
}
