package com.netmaxi.mm.api.papel;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import com.netmaxi.mm.api.role.Role;
import com.netmaxi.mm.api.role.RoleRepository;

@DataJpaTest
@ActiveProfiles("test")
class RoleRepositoryTest {
	
	@Autowired
	RoleRepository repo;

	@Test
	void testShouldFindAllRolesPageable() {
		Pageable pagination = Pageable.ofSize(1);
		Page<Role> roles = repo.findAll(pagination);
		assertTrue(roles.getNumberOfElements() > 0);
	}

	
	@Test
	void testShouldCreateRoleWithSpecificName(){
		Role role = new Role();
		role.setName("Role_OWNER");
		Role createdRole = repo.save(role);
		assertTrue(createdRole.getName().equals("Role_OWNER"));
	}

}
