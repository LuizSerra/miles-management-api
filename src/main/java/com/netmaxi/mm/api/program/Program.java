package com.netmaxi.mm.api.program;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.netmaxi.mm.api.miles.Miles;
import com.netmaxi.mm.api.program.dto.ProgramModifyDTO;
import com.netmaxi.mm.api.program.dto.ProgramRequestDTO;
import com.netmaxi.mm.api.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of = {"id", "name"})
@ToString(exclude = {"user", "miles"})
@Entity(name = "Program")
@Table( name = "programs")
public class Program {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private Integer balance;
	@Column(name = "balance_available")
	private Integer balanceAvailable;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User user;
	
	@OneToMany(mappedBy="program")
	@JsonManagedReference 
	private List<Miles> miles = new ArrayList<>();
	
	public Program() {}
	
	public Program(ProgramRequestDTO programRequestDTO) {
		this.name = programRequestDTO.name();
		this.description = programRequestDTO.description();
		this.balance = programRequestDTO.balance();
		this.balanceAvailable = programRequestDTO.balanceAvailable();
	}

	
	public void update(@Valid ProgramModifyDTO programUpdated) {
		
		if(programUpdated.name() != null) this.name = programUpdated.name();
		
		if(programUpdated.description() != null) this.description = programUpdated.description();
		
		if(programUpdated.balance() != null) this.balance = programUpdated.balance();
		
		if(programUpdated.balanceAvailable() != null) this.balanceAvailable = programUpdated.balanceAvailable();
		
	}
	
}
