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

import com.netmaxi.mm.api.programa.dto.ProgramaAtualizadoDTO;
import com.netmaxi.mm.api.programa.dto.ProgramaAtualizarDTO;
import com.netmaxi.mm.api.programa.dto.ProgramaEntradaDTO;
import com.netmaxi.mm.api.programa.dto.ProgramaRetornoDTO;
import com.netmaxi.mm.api.programa.service.ProgramaService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/programas")
public class ProgramController {
	
	private final ProgramaService programaService;
	
	public ProgramController(ProgramaService programaService) {
		this.programaService = programaService;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<ProgramaRetornoDTO> criar(@RequestBody @Valid ProgramaEntradaDTO programaDTO,	UriComponentsBuilder uriBuilder) {
		var programaCriado = programaService.criar(programaDTO);
		URI uri = uriBuilder.path("/programas/{id}").buildAndExpand(programaCriado.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProgramaRetornoDTO(programaCriado));
	}
	
	@GetMapping
	public ResponseEntity<Page<ProgramaRetornoDTO>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pag){
		var page = programaService.listar(pag);
		return ResponseEntity.ok(page.map(ProgramaRetornoDTO::new));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProgramaRetornoDTO> buscarPorId(@PathVariable Long id){
		var programaEncontrado = programaService.buscarPorId(id);
		return ResponseEntity.ok(new ProgramaRetornoDTO(programaEncontrado));
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<ProgramaAtualizadoDTO> atualizar(@RequestBody @Valid ProgramaAtualizarDTO programaDTO) {
		var programaAtualizado = programaService.atualizar(programaDTO);
		return ResponseEntity.ok(new ProgramaAtualizadoDTO(programaAtualizado));
	}
}
