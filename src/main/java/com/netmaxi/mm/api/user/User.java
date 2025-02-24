package com.netmaxi.mm.api.user;

import java.util.ArrayList;
import java.util.List;

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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of = {"id", "name", "email"})
@ToString(exclude = {"password", "programs"})
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

	public void update(@Valid UserModifyDTO userModifyDTO) {
		
		if(userModifyDTO.active() != null) this.active = userModifyDTO.active();
		if(userModifyDTO.name() != null) this.name = userModifyDTO.name();
		if(userModifyDTO.email() != null) this.email = userModifyDTO.email();
		if(userModifyDTO.password() != null) this.password = userModifyDTO.password();
		
	}
}
