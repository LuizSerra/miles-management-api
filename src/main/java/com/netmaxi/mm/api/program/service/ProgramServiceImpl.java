package com.netmaxi.mm.api.program.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.netmaxi.mm.api.program.Program;
import com.netmaxi.mm.api.program.ProgramRepository;
import com.netmaxi.mm.api.program.dto.ProgramModifyDTO;
import com.netmaxi.mm.api.program.dto.ProgramRequestDTO;
import com.netmaxi.mm.api.user.User;
import com.netmaxi.mm.api.user.service.UserService;

@Service
public class ProgramServiceImpl implements ProgramService {
	
	private final ProgramRepository programRepository;
	private final UserService userService;
	
	public ProgramServiceImpl(ProgramRepository programaRepository, UserService userService) {
		this.programRepository = programaRepository;
		this.userService = userService;
	}

	@Override
	public Program create(ProgramRequestDTO programRequestDTO) {
		Program program = new Program(programRequestDTO);
		User user = userService.findByID(programRequestDTO.user());
		program.setUser(user);
		programRepository.save(program);
		return program;
	}

	@Override
	public Page<Program> list(Pageable pag) {
		var page = programRepository.findAll(pag);
		return page;
	}

	@Override
	public Page<Program> listByUser(Long userID, Pageable pageable) {
		User user = userService.findByID(userID);
		var page = programRepository.findByUser(user, pageable);
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
