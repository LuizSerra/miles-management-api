package com.netmaxi.mm.api.transactions;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.netmaxi.mm.api.miles.Miles;
import com.netmaxi.mm.api.program.Program;
import com.netmaxi.mm.api.transactions.dto.TransactionRequestDTO;
import com.netmaxi.mm.api.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "transactions")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	private LocalDate date;
	
	@Temporal(TemporalType.DATE)
	private LocalDate expiration;

	private Integer amount;

	private BigDecimal price;
	
	@OneToOne
	@JoinColumn(name = "miles_id")
	private Miles miles;

	@Enumerated(EnumType.STRING)
	@Column(name = "transaction_type")
	private TransactionType transactionType;
	
	@ManyToOne
	@JoinColumn(name = "program_sender_id")
	private Program programSender;
	
	@ManyToOne
	@JoinColumn(name = "program_receiver_id")
	private Program programReceiver;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User user;
	
	public Transaction() {
		super();
	}

	public Transaction(TransactionRequestDTO transactionRequestDTO) {
		this.date = transactionRequestDTO.date();
		this.expiration = transactionRequestDTO.expiration();
		this.amount = transactionRequestDTO.amount();
		this.price = transactionRequestDTO.price();
		this.transactionType  = transactionRequestDTO.transactionType();
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

	public LocalDate getExpiration() {
		return expiration;
	}

	public void setExpiration(LocalDate expiration) {
		this.expiration = expiration;
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

	public Miles getMiles() {
		return miles;
	}

	public void setMiles(Miles miles) {
		this.miles = miles;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public Program getProgramSender() {
		return programSender;
	}

	public void setProgramSender(Program programSender) {
		this.programSender = programSender;
	}

	public Program getProgramReceiver() {
		return programReceiver;
	}

	public void setProgramReceiver(Program programReceiver) {
		this.programReceiver = programReceiver;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
