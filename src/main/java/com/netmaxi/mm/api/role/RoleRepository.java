package com.netmaxi.mm.api.role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByNameIgnoreCase(String name);
}
