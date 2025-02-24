package com.netmaxi.mm.api.miles.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.netmaxi.mm.api.miles.Miles;
import com.netmaxi.mm.api.miles.MilesRepository;
import com.netmaxi.mm.api.miles.dto.MilesModifyDTO;
import com.netmaxi.mm.api.miles.dto.MilesRequestDTO;
import com.netmaxi.mm.api.miles.dto.MilesResponseDTO;
import com.netmaxi.mm.api.program.Program;
import com.netmaxi.mm.api.program.ProgramRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MilesServiceImpl implements MilesService{

	private final ProgramRepository programRepository;
	private final MilesRepository milesRepository;
	
	public MilesServiceImpl(ProgramRepository programRepository, MilesRepository milesRepository) {
		this.programRepository = programRepository;
		this.milesRepository = milesRepository;
	}

	@Override
	public MilesResponseDTO create(MilesRequestDTO milesRequestDTO){
		log.info("CREATING MILES:::START");
		Miles miles = new Miles(milesRequestDTO);
		Program programFound = programRepository.getReferenceById(milesRequestDTO.program());
		log.info("CREATING MILES:::PROGRAM FOUND: {} ", programFound);
		miles.setProgram(programFound);
		var MilesCreated = milesRepository.save(miles);
		log.info("CREATING MILES:::END");
		return new MilesResponseDTO(MilesCreated);
	}

	@Override
	public Page<MilesResponseDTO> list(Pageable pag) {
		log.info("LISTING MILES:::START");
		var page = milesRepository.findAll(pag);
		log.info("LISTING MILES:::END");
		return page.map(MilesResponseDTO::new);
	}

	@Override
	public Page<MilesResponseDTO> listByProgram(Long programId, Pageable pageable) {
		log.info("LISTING MILES BY PROGRAM:::START");
		Program programFound = programRepository.getReferenceById(programId);
		log.info("LISTING MILES:::PROGRAM FOUND: {} ", programFound);
		var page = milesRepository.findByProgram(programFound, pageable);
		log.info("LISTING MILES BY PROGRAM:::END");
		return page.map(MilesResponseDTO::new);
	}

	@Override
	public MilesResponseDTO update(MilesModifyDTO milesModifyDTO) {
		log.info("UPDATING MILES:::START");
		var milesFound = milesRepository.getReferenceById(milesModifyDTO.id());
		log.info("UPDATING MILES:::MILES FOUND, {}", milesFound);
		milesFound.update(milesModifyDTO);
		log.info("UPDATING MILES:::START");
		return new MilesResponseDTO(milesFound);
	}

}
