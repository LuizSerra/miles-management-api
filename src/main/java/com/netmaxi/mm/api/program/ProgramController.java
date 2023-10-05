package com.netmaxi.mm.api.program;

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

import com.netmaxi.mm.api.program.dto.ProgramModifiedDTO;
import com.netmaxi.mm.api.program.dto.ProgramModifyDTO;
import com.netmaxi.mm.api.program.dto.ProgramRequestDTO;
import com.netmaxi.mm.api.program.dto.ProgramResponseDTO;
import com.netmaxi.mm.api.program.service.ProgramService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/programs")
public class ProgramController {
	
	private final ProgramService programaService;
	
	public ProgramController(ProgramService programaService) {
		this.programaService = programaService;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<ProgramResponseDTO> create(@RequestBody @Valid ProgramRequestDTO programaRequestDTO,	UriComponentsBuilder uriBuilder) {
		var programaCriado = programaService.create(programaRequestDTO);
		URI uri = uriBuilder.path("/programas/{id}").buildAndExpand(programaCriado.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProgramResponseDTO(programaCriado));
	}
	
	@GetMapping
	public ResponseEntity<Page<ProgramResponseDTO>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pag){
		var page = programaService.list(pag);
		return ResponseEntity.ok(page.map(ProgramResponseDTO::new));
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<Page<ProgramResponseDTO>> listByUser(@PageableDefault(size = 10, sort = {"name"}) Pageable pag, @PathVariable Long id){
		var page = programaService.listByUser(id, pag);
		return ResponseEntity.ok(page.map(ProgramResponseDTO::new));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProgramResponseDTO> findById(@PathVariable Long id){
		var programaEncontrado = programaService.findById(id);
		return ResponseEntity.ok(new ProgramResponseDTO(programaEncontrado));
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<ProgramModifiedDTO> atualizar(@RequestBody @Valid ProgramModifyDTO programaDTO) {
		var programaAtualizado = programaService.update(programaDTO);
		return ResponseEntity.ok(new ProgramModifiedDTO(programaAtualizado));
	}
}
