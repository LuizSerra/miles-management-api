package com.netmaxi.mm.api.usuario;

import java.util.ArrayList;
import java.util.List;

import com.netmaxi.mm.api.papel.Papel;
import com.netmaxi.mm.api.usuario.dto.UsuarioAtualizarDTO;
import com.netmaxi.mm.api.usuario.dto.UsuarioEntradaDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Boolean ativo;
	
	private String nome;
	
	@NotBlank
	@Column(unique = true)
	private String email;
	
	private String senha;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
	        name = "usuarios_papeis",
	        joinColumns = @JoinColumn(name = "usuario_id"),
	        inverseJoinColumns = @JoinColumn(name = "papel_id")
	    )
	private List<Papel> papeis = new ArrayList<>();
	
	
	public Usuario(UsuarioEntradaDTO usuario) {
		super();
		this.ativo = usuario.ativo();
		this.nome = usuario.nome();
		this.email = usuario.email();
		this.senha = usuario.senha();
	}

	public Usuario() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Papel> getPapeis() {
		return papeis;
	}

	public void setPapeis(List<Papel> papeis) {
		this.papeis = papeis;
	}

	public void atualiza(@Valid UsuarioAtualizarDTO usuAtualizarDTO) {
		
		if(usuAtualizarDTO.ativo() != null) this.ativo = usuAtualizarDTO.ativo();
		if(usuAtualizarDTO.nome() != null) this.nome = usuAtualizarDTO.nome();
		if(usuAtualizarDTO.email() != null) this.email = usuAtualizarDTO.email();
		if(usuAtualizarDTO.senha() != null) this.senha = usuAtualizarDTO.senha();
		
	}
	
}
