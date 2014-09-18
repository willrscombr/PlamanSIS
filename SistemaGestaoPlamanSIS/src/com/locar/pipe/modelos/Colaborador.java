package com.locar.pipe.modelos;

import java.io.Serializable;

public class Colaborador implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private String matricula;
	private String nome;
	private String sobrenome;
	private String cargo;
	private CentroTrabalho setor;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public CentroTrabalho getSetor() {
		return setor;
	}
	public void setSetor(CentroTrabalho setor) {
		this.setor = setor;
	}	
	
}
