package com.netmaxi.mm.api.program;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Miles> getMiles() {
		return miles;
	}

	public void setMiles(List<Miles> miles) {
		this.miles = miles;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Program other = (Program) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name) && Objects.equals(user, other.user);
	}
	
	
}
