package com.netmaxi.mm.api.miles;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.netmaxi.mm.api.miles.dto.MilesModifyDTO;
import com.netmaxi.mm.api.miles.dto.MilesRequestDTO;
import com.netmaxi.mm.api.miles.dto.MilesResponseDTO;
import com.netmaxi.mm.api.program.Program;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;

@Entity(name = "Miles")
@Table( name = "miles")
public class Miles {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer amount;
	private BigDecimal price;
	@Temporal(TemporalType.DATE)
	private LocalDate expiration;
	
	
	@ManyToOne
	@JoinColumn(name = "program_id", referencedColumnName = "id")
	@JsonBackReference
	private Program program;
	
	public Miles() {}
	
	public Miles(MilesRequestDTO milesRequestDTO) {
		this.amount = milesRequestDTO.amount();
		this.price = milesRequestDTO.price();
		this.expiration = milesRequestDTO.expiration();
	}

	public Miles(Integer amount, BigDecimal price, LocalDate expiration, Program program) {
		this.amount = amount;
		this.price = price;
		this.expiration = expiration;
		this.program = program;
	}

	public Miles(MilesResponseDTO milesResponse) {
		this.id = milesResponse.id();
		this.amount = milesResponse.amount();
		this.price = milesResponse.price();
		this.expiration = milesResponse.expiration();
		this.program = milesResponse.program();
	}
	
	public void update(@Valid MilesModifyDTO milesModifyDTO) {
		
		if(milesModifyDTO.amount() != null) this.amount = milesModifyDTO.amount();
		
		if(milesModifyDTO.price() != null) this.price = milesModifyDTO.price();
		
		if(milesModifyDTO.expiration() != null) this.expiration = milesModifyDTO.expiration();
		
		if(milesModifyDTO.program() != null) this.program = milesModifyDTO.program();
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public LocalDate getExpiration() {
		return expiration;
	}

	public void setExpiration(LocalDate expiration) {
		this.expiration = expiration;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}
	
}
