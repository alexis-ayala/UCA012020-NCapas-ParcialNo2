package com.uca.capas.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;

import com.uca.capas.domain.Categoria;
import com.uca.capas.domain.Libro;

public interface LibroDAO {
	
	public List<Libro> findAll() throws DataAccessException;
	public List<Categoria> findCatAll() throws DataAccessException;
	public Categoria findCatOne(Integer id) throws DataAccessException;
	
	public void saveLibro(Libro libro) throws DataAccessException;
	public void saveCategoria(Categoria categoria) throws DataAccessException;
	//ca
}
