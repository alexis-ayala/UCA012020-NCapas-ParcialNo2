package com.uca.capas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uca.capas.domain.Categoria;
import com.uca.capas.domain.Libro;

@Repository
public class LibroDAOImpl implements LibroDAO {

	@PersistenceContext(unitName="capas")
	private EntityManager entityManager;
	
	@Override
	public List<Libro> findAll() throws DataAccessException {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from public.cat_libro");
		Query query = entityManager.createNativeQuery(sb.toString(), Libro.class);
		List<Libro> res = query.getResultList();
		return res;
	}
	@Override
	public Categoria findCatOne(Integer id) throws DataAccessException {
		// TODO Auto-generated method stub
		Categoria cat = entityManager.find(Categoria.class,id);
		return cat;
	}
	@Override
	public List<Categoria> findCatAll() throws DataAccessException {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from public.cat_categoria");
		Query query = entityManager.createNativeQuery(sb.toString(), Categoria.class);
		List<Categoria> res = query.getResultList();
		return res;
	}
	@Override
	@Transactional
	public void saveLibro(Libro libro) throws DataAccessException {
		
		if(libro.getClibro() == null) { //Si la propiedad de la llave primaria viene vacío, entonces es un INSERT
			entityManager.persist(libro); //Utilizamos persist ya que es un INSERT
		}
		
	}
	@Override
	@Transactional
	public void saveCategoria(Categoria categoria) throws DataAccessException {
		if(categoria.getCcategoria() == null) { //Si la propiedad de la llave primaria viene vacío, entonces es un INSERT
			entityManager.persist(categoria); //Utilizamos persist ya que es un INSERT
		}
		
	}
}
