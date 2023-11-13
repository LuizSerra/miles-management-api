package com.netmaxi.mm.api.miles;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.netmaxi.mm.api.program.Program;

public interface MilesRepository extends JpaRepository<Miles, Long> { 
	Page<Miles> findByProgram(Program program, Pageable pageable);
}
