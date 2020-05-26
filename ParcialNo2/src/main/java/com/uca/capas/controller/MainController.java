package com.uca.capas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.dao.LibroDAO;
import com.uca.capas.domain.Categoria;
import com.uca.capas.domain.Libro;

@Controller
public class MainController {
	@Autowired
	private LibroDAO libroDAO;
	
	@RequestMapping("/verlibro")
	public ModelAndView initMain() {
		ModelAndView mav = new ModelAndView();
		List<Libro> libros = null;
		try {
			libros = libroDAO.findAll();
		}catch(Exception e) {
			e.printStackTrace();
		}
		mav.addObject("libros",libros);
		mav.setViewName("verlibro");
		return mav;
	}
	@RequestMapping("/ingresarlibv")
	public ModelAndView ingresarlibv(@Valid @ModelAttribute Libro libro, BindingResult result) {
		ModelAndView mav = new ModelAndView();	
		if(result.hasErrors()) {
			mav.setViewName("ingresarlib");
			List<Categoria> cate = libroDAO.findCatAll();
			mav.addObject("categorias", cate);
		}else {
			try {
				libro.setFecha();
				libro.setCategoria(libroDAO.findCatOne(libro.getCcategoria()));
				libroDAO.saveLibro(libro);
				mav = new ModelAndView("redirect:/index?tipo=1");
				return mav;
			}catch(Exception e) {
				//mav.setViewName("index");
				e.printStackTrace();
			}
		}
		return mav;
	}
	@RequestMapping("/ingresarcatv")
	public ModelAndView ingresarcatv(@Valid @ModelAttribute Categoria categoria, BindingResult result) {
		ModelAndView mav = new ModelAndView();	
		if(result.hasErrors()) {
			mav.setViewName("ingresarcat");
		}else {
			try {
				libroDAO.saveCategoria(categoria);
				mav = new ModelAndView("redirect:/index?tipo=2");
				
				return mav;
			}catch(Exception e) {
				//mav.setViewName("index");
				e.printStackTrace();
			}
		}
		return mav;
	}
	@RequestMapping("/")
	public ModelAndView index(@RequestParam(required=false) Integer tipo) {
		ModelAndView mav = new ModelAndView("index");
		if(tipo!=null) {
			if(tipo==1) {
				mav.addObject("resultado", "Libro guardado con éxito");
			}else {
				mav.addObject("resultado", "Categoría guardada con éxito");
			}
		}
		
		
		return mav;
	}
	@RequestMapping("/index")
	public ModelAndView inde(@RequestParam(required=false) Integer tipo) {
		ModelAndView mav = new ModelAndView("index");
		if(tipo!=null) {
			if(tipo==1) {
				mav.addObject("resultado", "Libro guardado con éxito");
			}else {
				mav.addObject("resultado", "Categoría guardada con éxito");
			}
		}
		return mav;
	}
	@RequestMapping("/ingresarcat")
	public ModelAndView ingresarcat(Categoria categoria) {
		ModelAndView mav = new ModelAndView("ingresarcat");
		List<Categoria> cate = libroDAO.findCatAll();
		mav.addObject("categorias", cate);
		return mav;
	}
	@RequestMapping("/ingresarlib")
	public ModelAndView index(Libro libro) {
		ModelAndView mav = new ModelAndView("ingresarlib");
		List<Categoria> cate = libroDAO.findCatAll();
		mav.addObject("categorias", cate);
		return mav;
	}
}
