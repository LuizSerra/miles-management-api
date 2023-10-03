package com.netmaxi.mm.api.program;

import com.netmaxi.mm.api.program.dto.ProgramModifyDTO;
import com.netmaxi.mm.api.program.dto.ProgramRequestDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;

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
	
	public Program() {}
	
	public Program(ProgramRequestDTO programRequestDTO) {
		this.name = programRequestDTO.name();
		this.description = programRequestDTO.description();
		this.balance = programRequestDTO.balance();
		this.balanceAvailable = programRequestDTO.balanceAvailable();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public Integer getBalanceAvailable() {
		return balanceAvailable;
	}

	public void setBalanceAvailable(Integer balanceAvailable) {
		this.balanceAvailable = balanceAvailable;
	}

	public void update(@Valid ProgramModifyDTO programUpdated) {
		
		if(programUpdated.name() != null) this.name = programUpdated.name();
		
		if(programUpdated.description() != null) this.description = programUpdated.description();
		
		if(programUpdated.balance() != null) this.balance = programUpdated.balance();
		
		if(programUpdated.balanceAvailable() != null) this.balanceAvailable = programUpdated.balanceAvailable();
		
	}

}
