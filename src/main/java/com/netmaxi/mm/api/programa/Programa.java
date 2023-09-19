package com.netmaxi.mm.api.programa;

import com.netmaxi.mm.api.programa.dto.ProgramaAtualizarDTO;
import com.netmaxi.mm.api.programa.dto.ProgramaEntradaDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;

@Entity(name = "Programa")
@Table( name = "programas")
public class Programa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String descricao;
		
	public Programa() {}
	
	public Programa(ProgramaEntradaDTO programEntradaDTO) {
		this.nome = programEntradaDTO.nome();
		this.descricao = programEntradaDTO.descricao();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void atualiza(@Valid ProgramaAtualizarDTO programaDTO) {
		
		if(programaDTO.nome() != null) this.nome = programaDTO.nome();
		
		if(programaDTO.descricao() != null) this.descricao = programaDTO.descricao();
		
	}

}
