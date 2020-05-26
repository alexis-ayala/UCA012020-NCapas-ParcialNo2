package com.uca.capas.domain;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(schema = "public", name = "cat_libro")
public class Libro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "c_libro")
	private Integer clibro;
	
	@NotEmpty(message="El campo no puede estar vacío")
	@Size(message="El campo sobrepasa la cantidad de 500 caracteres", max = 500)
	@Column(name = "s_titulo")
	private String stitulo;
	
	@NotEmpty(message="El campo no puede estar vacío")
	@Size(message="El campo sobrepasa la cantidad de 150 caracteres", max = 150)
	@Column(name = "s_autor")
	private String sautor;
	
	@NotEmpty(message="El campo no puede estar vacío")
	@Size(message="El campo sobrepasa la cantidad de 10 caracteres", max = 10)
	@Column(name = "ISBN")
	private String isbn;
	
	@Column(name = "f_ingreso")
	private Calendar fingreso;
	
	@Column(name = "b_estado")
	private Boolean bestado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "c_categoria")
	private Categoria categoria;
	
	@Transient
	private Integer ccategoria;

	public Integer getClibro() {
		return clibro;
	}

	public void setClibro(Integer clibro) {
		this.clibro = clibro;
	}

	public String getStitulo() {
		return stitulo;
	}

	public void setStitulo(String stitulo) {
		this.stitulo = stitulo;
	}

	public String getSautor() {
		return sautor;
	}

	public void setSautor(String sautor) {
		this.sautor = sautor;
	}

	public Calendar getFingreso() {
		return fingreso;
	}

	public void setFingreso(Calendar fingreso) {
		this.fingreso = fingreso;
	}
	public String getFingresoDelegate(){
		if(this.fingreso == null){
			return "";
		}
		else{
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
			String shortdate = sdf.format(this.fingreso.getTime());
			return shortdate.toUpperCase();
		}
	}
	public Boolean getBestado() {
		return bestado;
	}

	public void setBestado(Boolean bestado) {
		this.bestado = bestado;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Integer getCcategoria() {
		return ccategoria;
	}

	public void setCcategoria(Integer ccategoria) {
		this.ccategoria = ccategoria;
	}
	
	

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public void setFecha() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		this.fingreso = Calendar.getInstance();
	}
		
	public Libro() {
	}

	
}
