package com.netmaxi.mm.api.program.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.netmaxi.mm.api.program.Program;
import com.netmaxi.mm.api.program.dto.ProgramModifyDTO;
import com.netmaxi.mm.api.program.dto.ProgramRequestDTO;

public interface ProgramService {
	
	public Program create(ProgramRequestDTO programRequestDTO);
	
	public Page<Program> list(Pageable pag);
	
	public Program findById(Long id);
	
	public Program update(ProgramModifyDTO programDTO);
	
	public Boolean validateTransaction(Program programa, Integer value) throws Exception;

}
