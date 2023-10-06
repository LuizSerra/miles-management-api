package com.netmaxi.mm.api.program;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.netmaxi.mm.api.user.User;

public interface ProgramRepository extends JpaRepository<Program, Long> { 

	Page<Program> findByUser(User user, Pageable pageable);
	Program findByNameAndUser(String name, User user);
}
