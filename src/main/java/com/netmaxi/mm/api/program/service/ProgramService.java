package com.netmaxi.mm.api.program.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.netmaxi.mm.api.program.dto.ProgramModifiedDTO;
import com.netmaxi.mm.api.program.dto.ProgramModifyDTO;
import com.netmaxi.mm.api.program.dto.ProgramRequestDTO;
import com.netmaxi.mm.api.program.dto.ProgramResponseDTO;

public interface ProgramService {
	
	public ProgramResponseDTO create(ProgramRequestDTO programRequestDTO);
	
	public Page<ProgramResponseDTO> list(Pageable pag);
	
	public ProgramResponseDTO findById(Long id);
	
	public Page<ProgramResponseDTO> listByUser(Long userID, Pageable pageable);
	
	public ProgramModifiedDTO update(ProgramModifyDTO programDTO);
	
	public Boolean validateTransaction(ProgramRequestDTO programa, Integer value) throws Exception;

}
