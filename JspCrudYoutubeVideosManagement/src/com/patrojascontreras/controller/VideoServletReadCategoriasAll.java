package com.patrojascontreras.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;

import java.util.*;

import org.apache.log4j.Logger;

import com.patrojascontreras.dto.Categoria;
import com.patrojascontreras.dao.CategoriasDAO;

/**
 * Servlet implementation class VideoServletReadCategoriasAll
 */
@WebServlet("/VideoServletReadCategoriasAll")
public class VideoServletReadCategoriasAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(VideoServletReadCategoriasAll.class.getName());
	
	private CategoriasDAO dao;
	
    public VideoServletReadCategoriasAll() {
        super();
        dao = new CategoriasDAO();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		try {
			List<Categoria> listadoCategorias = dao.listadoCategorias();
			
			out.println("<option value=\"\">Seleccione</option>");
			
			for(Categoria cat : listadoCategorias) {
				int idCategoria = cat.getIdCategoria();
				String categoria = cat.getCategoria();
				
				out.print("<option value=" + idCategoria + ">" + categoria + "</option>");
			}
			
			log.debug("Se ha llamado al Servlet mediante un proceso de tipo GET");
		} catch(Exception e) {
			System.out.println(e);
		}
	}

}
