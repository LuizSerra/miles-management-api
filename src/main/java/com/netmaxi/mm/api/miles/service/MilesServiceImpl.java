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
		Miles miles = new Miles(milesRequestDTO);
		Program programFound = programRepository.getReferenceById(milesRequestDTO.program());
		miles.setProgram(programFound);
		var MilesCreated = milesRepository.save(miles);
		return new MilesResponseDTO(MilesCreated);
	}

	@Override
	public Page<MilesResponseDTO> list(Pageable pag) {
		var page = milesRepository.findAll(pag);
		return page.map(MilesResponseDTO::new);
	}

	@Override
	public Page<MilesResponseDTO> listByProgram(Long programId, Pageable pageable) {
		Program programFound = programRepository.getReferenceById(programId);
		var page = milesRepository.findByProgram(programFound, pageable);
		return page.map(MilesResponseDTO::new);
	}

	@Override
	public MilesResponseDTO update(MilesModifyDTO milesModifyDTO) {
		var milesFound = milesRepository.getReferenceById(milesModifyDTO.id());
		milesFound.update(milesModifyDTO);
		return new MilesResponseDTO(milesFound);
	}

}
