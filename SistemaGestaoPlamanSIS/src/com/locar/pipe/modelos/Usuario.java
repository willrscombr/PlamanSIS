package com.locar.pipe.modelos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="tb_usuario")
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column(name="nome_usuario")
	private String nomeUsuario;
	private String senha;
	
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
