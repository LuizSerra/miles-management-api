package com.netmaxi.mm.api.programa;

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
	public ResponseEntity<ProgramaAtualizadoDTO> criar(@RequestBody @Valid ProgramaEntradaDTO programaDTO, UriComponentsBuilder uriBuilder) {
		return programaService.criar(programaDTO, uriBuilder);
	}
	
	@GetMapping
	public ResponseEntity<Page<ProgramaRetornoDTO>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pag){
		return programaService.listar(pag);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProgramaAtualizadoDTO> buscarPorId(@PathVariable Long id){
		return programaService.buscarPorId(id);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<ProgramaAtualizadoDTO> atualizar(@RequestBody @Valid ProgramaAtualizarDTO programaDTO) {
		return programaService.atualizar(programaDTO);
	}
}
