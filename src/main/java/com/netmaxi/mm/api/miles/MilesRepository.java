package com.netmaxi.mm.api.miles;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.netmaxi.mm.api.program.Program;

public interface MilesRepository extends JpaRepository<Miles, Long> { 
	Page<Miles> findByProgram(Program program, Pageable pageable);
	
	@Query("SELECT m FROM Miles m " +
	           "WHERE m.program.id = :programId " +
	           "AND m.expiration >= :currentDate ")
	    List<Miles> findMilesBasedOnCriteria(Long programId, LocalDate currentDate);
}
