package com.netmaxi.mm.api.program.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.netmaxi.mm.api.program.Program;
import com.netmaxi.mm.api.program.ProgramRepository;
import com.netmaxi.mm.api.program.dto.ProgramModifyDTO;
import com.netmaxi.mm.api.program.dto.ProgramRequestDTO;

@Service
public class ProgramServiceImpl implements ProgramService {
	
	private final ProgramRepository programRepository;
	
	public ProgramServiceImpl(ProgramRepository programaRepository) {
		this.programRepository = programaRepository;
	}

	@Override
	public Program create(ProgramRequestDTO programRequestDTO) {
		Program program = new Program(programRequestDTO);
		programRepository.save(program);
		return program;
	}

	@Override
	public Page<Program> list(Pageable pag) {
		var page = programRepository.findAll(pag);
		return page;
	}

	@Override
	public Program findById(Long id) {
		var programFound = programRepository.getReferenceById(id);
		return programFound;
	}

	@Override
	public Program update(ProgramModifyDTO programModifyDTO) {
		var programFound = findById(programModifyDTO.id());
		programFound.update(programModifyDTO);
		return programFound;
	}

	@Override
	public Boolean validateTransaction(Program sourceProgram, Integer amount) throws Exception {
		if(sourceProgram.getBalanceAvailable().compareTo(amount) < 0) {
			throw new Exception("The balance is not enough to complete the transaction");
		}
		return Boolean.TRUE;
	}

}
