package com.netmaxi.mm.api.papel;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class PapelRepositoryTest {
	
	@Autowired
	PapelRepository repo;

	@Test
	void testFindAllPageable() {
		List<Papel> papeis = repo.findAll();
		assertTrue(papeis.size() > 0);
	}

}
