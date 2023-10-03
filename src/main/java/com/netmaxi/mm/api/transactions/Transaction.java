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

	@Enumerated(EnumType.STRING)
	@Column(name = "transaction_type")
	private TransactionType transactionType;

	private Integer amount;

	private BigDecimal value;
	
	@ManyToOne
	@JoinColumn(name = "source_program_id")
	private Program sourceProgram;

	@ManyToOne
	@JoinColumn(name = "target_program_id")
	private Program targetProgram;

	public Transaction() {
		super();
	}

	public Transaction(TransactionRequestDTO transactionRequestDTO) {
		this.date = transactionRequestDTO.date();
		this.transactionType  = transactionRequestDTO.transactionType();
		this.amount = transactionRequestDTO.amount();
		this.value = transactionRequestDTO.value();
		this.sourceProgram = transactionRequestDTO.sourceProgram();
		this.targetProgram = transactionRequestDTO.targetProgram();
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

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Program getSourceProgram() {
		return sourceProgram;
	}

	public void setSourceProgram(Program sourceProgram) {
		this.sourceProgram = sourceProgram;
	}

	public Program getTargetProgram() {
		return targetProgram;
	}

	public void setTargetProgram(Program targetProgram) {
		this.targetProgram = targetProgram;
	}

}
