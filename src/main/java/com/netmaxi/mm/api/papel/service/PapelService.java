package com.netmaxi.mm.api.papel.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.netmaxi.mm.api.papel.Papel;
import com.netmaxi.mm.api.papel.dto.PapelEntradaDTO;

public interface PapelService {
	
	public Papel criar(PapelEntradaDTO papelDTO);
	
	public Page<Papel> listar(Pageable pag);

}
