package com.netmaxi.mm.api.program.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.netmaxi.mm.api.error.TransactionBusinessException;
import com.netmaxi.mm.api.error.ValidationException;
import com.netmaxi.mm.api.miles.Miles;
import com.netmaxi.mm.api.miles.MilesRepository;
import com.netmaxi.mm.api.program.Program;
import com.netmaxi.mm.api.program.ProgramRepository;
import com.netmaxi.mm.api.program.dto.ProgramModifiedDTO;
import com.netmaxi.mm.api.program.dto.ProgramModifyDTO;
import com.netmaxi.mm.api.program.dto.ProgramRequestDTO;
import com.netmaxi.mm.api.program.dto.ProgramResponseDTO;
import com.netmaxi.mm.api.user.User;
import com.netmaxi.mm.api.user.UserRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ProgramServiceImpl implements ProgramService {
	
	private final ProgramRepository programRepository;
	private final UserRepository userRepository;
	private final MilesRepository milesRepository;
	
	public ProgramServiceImpl(ProgramRepository programRepository, UserRepository userRepository,
			MilesRepository milesRepository) {
		this.programRepository = programRepository;
		this.userRepository = userRepository;
		this.milesRepository = milesRepository;
	}

	@Override
	public ProgramResponseDTO create(ProgramRequestDTO programRequestDTO) throws Exception {
		log.info("CREATING PROGRAM:::START");
		Program program = new Program(programRequestDTO);
		User user = userRepository.getReferenceById(programRequestDTO.user());
		log.info("CREATING PROGRAM:::USER FOUND: {} ", user);
		if(programRepository.findByNameAndUser(programRequestDTO.name(), user) != null) {
			log.info("CREATING PROGRAM:::EXISTING PROGRAM FOR THIS USER::: EXCEPTION THROWN");
			throw new ValidationException("A program with this name already exist for this user.");
		}
		program.setUser(user);
		Miles miles = new Miles(programRequestDTO.balanceAvailable(), BigDecimal.ZERO, LocalDate.now().plusYears(10), program);
		milesRepository.save(miles);
		log.info("CREATING PROGRAM:::MILES CREATED");
		program.getMiles().add(miles);
		log.info("CREATING PROGRAM:::MILES ADDED TO PROGRAM");
		programRepository.save(program);
		log.info("CREATING PROGRAM:::END");
		return new ProgramResponseDTO(program);
	}

	@Override
	public Page<ProgramResponseDTO> list(Pageable pag) {
		var page = programRepository.findAll(pag);
		return page.map(ProgramResponseDTO::new);
	}

	@Override
	public Page<ProgramResponseDTO> listByUser(Long userID, Pageable pageable) {
		log.info("LISTING PROGRAM:::START");
		User user = userRepository.getReferenceById(userID);
		log.info("LISTING PROGRAM:::USER FOUND: {} ", user);
		var page = programRepository.findByUser(user, pageable);
		log.info("LISTING PROGRAM:::END");
		return page.map(ProgramResponseDTO::new);
	}
	
	@Override
	public ProgramResponseDTO findById(Long id) {
		log.info("FINDING PROGRAM BY ID:::START");
		var programFound = programRepository.getReferenceById(id);
		log.info("FINDING PROGRAM BY ID:::START");
		return new ProgramResponseDTO(programFound);
	}

	@Override
	public ProgramModifiedDTO update(ProgramModifyDTO programModifyDTO) {
		log.info("UPDATING PROGRAM:::START");
		var programFound = programRepository.getReferenceById(programModifyDTO.id());
		log.info("UPDATING PROGRAM:::PROGRAM FOUND, {}", programFound);
		programFound.update(programModifyDTO);
		log.info("UPDATING PROGRAM:::END");
		return new ProgramModifiedDTO(programFound);
	}

	@Override
	public void validateTransaction(Integer balance, Integer amount) throws TransactionBusinessException {
		log.info("VALIDATING TRANSACTION:::START");
		if(balance.compareTo(amount) < 0) {
			log.info("VALIDATING TRANSACTION:::INVALID");
			throw new TransactionBusinessException("The balance is not enough to complete the transaction");
		}
		log.info("VALIDATING TRANSACTION:::END");
	}

}
