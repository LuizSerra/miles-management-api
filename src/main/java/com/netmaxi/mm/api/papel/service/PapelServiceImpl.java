package com.netmaxi.mm.api.papel.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.netmaxi.mm.api.papel.Papel;
import com.netmaxi.mm.api.papel.PapelRepository;
import com.netmaxi.mm.api.papel.dto.PapelEntradaDTO;

@Service
public class PapelServiceImpl implements PapelService {
	
	private final PapelRepository papelRepository;
	
	public PapelServiceImpl(PapelRepository papelRepository) {
		this.papelRepository = papelRepository;
	}

	@Override
	public Papel criar(PapelEntradaDTO papelDTO) {
		Papel papel = new Papel(papelDTO);
		papelRepository.save(papel);
		return papel;
	}

	@Override
	public Page<Papel> listar(Pageable pag) {
		Page<Papel> page = papelRepository.findAll(pag);
		return page;
	}

}
