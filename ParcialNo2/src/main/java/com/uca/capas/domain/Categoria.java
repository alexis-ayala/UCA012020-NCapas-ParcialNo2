package com.uca.capas.domain;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(schema="public",name="cat_categoria")
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "c_categoria")
	private Integer ccategoria;
	
	@NotEmpty(message="El campo no puede estar vacío")
	@Size(message="El campo sobrepasa la cantidad de 50 caracteres", max = 50)
	@Column(name = "s_categoria")
	private String scategoria;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY)
	private List<Libro> libros;

	public Categoria() {
		
	}


	public Integer getCcategoria() {
		return ccategoria;
	}


	public void setCcategoria(Integer ccategoria) {
		this.ccategoria = ccategoria;
	}


	public String getScategoria() {
		return scategoria;
	}


	public void setScategoria(String scategoria) {
		this.scategoria = scategoria;
	}


	public List<Libro> getLibros() {
		return libros;
	}


	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}
	
	
}
