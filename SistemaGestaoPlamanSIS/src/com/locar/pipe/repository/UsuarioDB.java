package com.locar.pipe.repository;

import java.util.List;

import com.locar.pipe.modelos.Usuario;

public interface UsuarioDB {

	void salvar(Usuario user);
	List<Usuario> listarTodos();
	void update(Usuario user);
	void delete(Usuario user);
	Usuario buscarPorNome(String nome);
}
