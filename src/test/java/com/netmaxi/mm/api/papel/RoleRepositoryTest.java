package com.netmaxi.mm.api.papel;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.netmaxi.mm.api.role.Role;
import com.netmaxi.mm.api.role.RoleRepository;

@DataJpaTest
@ActiveProfiles("test")
class RoleRepositoryTest {
	
	@Autowired
	RoleRepository repo;

	@Test
	void testFindAllPageable() {
		List<Role> roles = repo.findAll();
		assertTrue(roles.size() > 0);
	}

	
	@Test
	void testCreate(){
		Role role = new Role();
		role.setName("Role_OWNER");
		Role createdRole = repo.save(role);
		assertTrue(createdRole.getName().equals("Role_OWNER"));
	}

}
