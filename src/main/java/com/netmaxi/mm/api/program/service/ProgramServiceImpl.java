package com.netmaxi.mm.api.program.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.netmaxi.mm.api.error.EntityAlreadyCreatedException;
import com.netmaxi.mm.api.program.Program;
import com.netmaxi.mm.api.program.ProgramRepository;
import com.netmaxi.mm.api.program.dto.ProgramModifiedDTO;
import com.netmaxi.mm.api.program.dto.ProgramModifyDTO;
import com.netmaxi.mm.api.program.dto.ProgramRequestDTO;
import com.netmaxi.mm.api.program.dto.ProgramResponseDTO;
import com.netmaxi.mm.api.user.User;
import com.netmaxi.mm.api.user.UserRepository;

@Service
public class ProgramServiceImpl implements ProgramService {
	
	private final ProgramRepository programRepository;
	private final UserRepository userRepository;
	
	public ProgramServiceImpl(ProgramRepository programaRepository, UserRepository userRepository) {
		this.programRepository = programaRepository;
		this.userRepository = userRepository;
	}

	@Override
	public ProgramResponseDTO create(ProgramRequestDTO programRequestDTO) throws Exception {
		Program program = new Program(programRequestDTO);
		User user = userRepository.getReferenceById(programRequestDTO.user());
		if(programRepository.findByNameAndUser(programRequestDTO.name(), user) != null) {
			throw new EntityAlreadyCreatedException("A program with this name already exist for this user.");
		}
		program.setUser(user);
		programRepository.save(program);
		return new ProgramResponseDTO(program);
	}

	@Override
	public Page<ProgramResponseDTO> list(Pageable pag) {
		var page = programRepository.findAll(pag);
		return page.map(ProgramResponseDTO::new);
	}

	@Override
	public Page<ProgramResponseDTO> listByUser(Long userID, Pageable pageable) {
		User user = userRepository.getReferenceById(userID);
		var page = programRepository.findByUser(user, pageable);
		return page.map(ProgramResponseDTO::new);
	}
	
	@Override
	public ProgramResponseDTO findById(Long id) {
		var programFound = programRepository.getReferenceById(id);
		return new ProgramResponseDTO(programFound);
	}

	@Override
	public ProgramModifiedDTO update(ProgramModifyDTO programModifyDTO) {
		var programFound = programRepository.getReferenceById(programModifyDTO.id());
		programFound.update(programModifyDTO);
		return new ProgramModifiedDTO(programFound);
	}

	@Override
	public Boolean validateTransaction(ProgramRequestDTO programrequest, Integer amount) throws Exception {
		if(programrequest.balanceAvailable().compareTo(amount) < 0) {
			throw new Exception("The balance is not enough to complete the transaction");
		}
		return Boolean.TRUE;
	}

}
