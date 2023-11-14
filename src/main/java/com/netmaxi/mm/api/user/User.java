package com.netmaxi.mm.api.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.netmaxi.mm.api.program.Program;
import com.netmaxi.mm.api.role.Role;
import com.netmaxi.mm.api.user.dto.UserModifyDTO;
import com.netmaxi.mm.api.user.dto.UserRequestDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Boolean active;
	
	private String name;
	
	@NotBlank
	@Column(unique = true)
	private String email;
	
	private String password;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
	        name = "users_roles",
	        joinColumns = @JoinColumn(name = "user_id"),
	        inverseJoinColumns = @JoinColumn(name = "role_id")
	    )
	private List<Role> roles = new ArrayList<>();
	
	@OneToMany(mappedBy="user")
	@JsonManagedReference 
	private List<Program> programs = new ArrayList<>();
	 
	public User(UserRequestDTO user) {
		super();
		this.active = user.active();
		this.name = user.name();
		this.email = user.email();
		this.password = user.password();
	}

	public User() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Program> getPrograms() {
		return programs;
	}

	public void setPrograms(List<Program> programs) {
		this.programs = programs;
	}
	
	public void update(@Valid UserModifyDTO userModifyDTO) {
		
		if(userModifyDTO.active() != null) this.active = userModifyDTO.active();
		if(userModifyDTO.name() != null) this.name = userModifyDTO.name();
		if(userModifyDTO.email() != null) this.email = userModifyDTO.email();
		if(userModifyDTO.password() != null) this.password = userModifyDTO.password();
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
	
	
}
