package com.netmaxi.mm.api.programa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public Programa criar(@RequestBody @Valid ProgramaEntradaDTO programaDTO) {
		return programaRepository.save(new Programa(programaDTO));
	}
	
	@GetMapping
	public Page<ProgramaRetornoDTO> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pag){
		return programaRepository.findAll(pag).map(ProgramaRetornoDTO::new);
	}

	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid ProgramaAtualizarDTO programaDTO) {
		var programa = programaRepository.getReferenceById(programaDTO.id());
		programa.atualiza(programaDTO);
		
	}
}
