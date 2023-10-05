package com.netmaxi.mm.api.transactions;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.netmaxi.mm.api.program.Program;
import com.netmaxi.mm.api.transactions.dto.TransactionRequestDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "transactions")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.DATE)
	private LocalDate date;

	private Integer amount;

	private BigDecimal price;

	@Enumerated(EnumType.STRING)
	@Column(name = "transaction_type")
	private TransactionType transactionType;
	
	@ManyToOne
	@JoinColumn(name = "program_id")
	private Program program;

	public Transaction() {
		super();
	}

	public Transaction(TransactionRequestDTO transactionRequestDTO) {
		this.date = transactionRequestDTO.date();
		this.amount = transactionRequestDTO.amount();
		this.price = transactionRequestDTO.value();
		this.transactionType  = transactionRequestDTO.transactionType();
		this.program = transactionRequestDTO.program();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
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

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

}
