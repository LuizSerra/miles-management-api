package com.netmaxi.mm.api.programa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.netmaxi.mm.api.programa.Programa;
import com.netmaxi.mm.api.programa.ProgramaRepository;
import com.netmaxi.mm.api.programa.dto.ProgramaAtualizarDTO;
import com.netmaxi.mm.api.programa.dto.ProgramaEntradaDTO;

@Service
public class ProgramaServiceImpl implements ProgramaService {
	
	private final ProgramaRepository programaRepository;
	
	public ProgramaServiceImpl(ProgramaRepository programaRepository) {
		this.programaRepository = programaRepository;
	}

	@Override
	public Programa criar(ProgramaEntradaDTO programaDTO) {
		Programa programa = new Programa(programaDTO);
		programaRepository.save(programa);
		return programa;
	}

	@Override
	public Page<Programa> listar(Pageable pag) {
		var page = programaRepository.findAll(pag);
		return page;
	}

	@Override
	public Programa buscarPorId(Long id) {
		var programaEncontrado = programaRepository.getReferenceById(id);
		return programaEncontrado;
	}

	@Override
	public Programa atualizar(ProgramaAtualizarDTO programaDTO) {
		var programaEncontrado = buscarPorId(programaDTO.id());
		programaEncontrado.atualiza(programaDTO);
		return programaEncontrado;
	}

}
