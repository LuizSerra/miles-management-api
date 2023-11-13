package com.netmaxi.mm.api.miles.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.netmaxi.mm.api.miles.dto.MilesModifyDTO;
import com.netmaxi.mm.api.miles.dto.MilesRequestDTO;
import com.netmaxi.mm.api.miles.dto.MilesResponseDTO;

public interface MilesService {

	public MilesResponseDTO create(MilesRequestDTO milesRequestDTO);
	
	public Page<MilesResponseDTO> list(Pageable pag);
	
	public Page<MilesResponseDTO> listByProgram(Long programId, Pageable pageable);
	
	public MilesResponseDTO update(MilesModifyDTO milesModifyDTO);

	
}
