package com.netmaxi.mm.api.programa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.netmaxi.mm.api.programa.Programa;
import com.netmaxi.mm.api.programa.dto.ProgramaAtualizarDTO;
import com.netmaxi.mm.api.programa.dto.ProgramaEntradaDTO;

public interface ProgramaService {
	
	public Programa criar(ProgramaEntradaDTO programaDTO);
	
	public Page<Programa> listar(Pageable pag);
	
	public Programa buscarPorId(Long id);
	
	public Programa atualizar(ProgramaAtualizarDTO programaDTO);

}
