package com.patrojascontreras.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.patrojascontreras.dto.Video;
import com.patrojascontreras.dao.VideosDAO;

/**
 * Servlet implementation class VideoServletInsert
 */
@WebServlet("/VideoServletInsert")
public class VideoServletInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(VideoServletInsert.class.getName());
	
	private VideosDAO dao;
	
	private boolean hasError = false;
	
    public VideoServletInsert() {
        super();
        dao = new VideosDAO();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		JSONObject respuesta = new JSONObject();
		
		String descripcion = "";
		
		try {
			Video video = new Video();
			
			String url = request.getParameter("urlVideo");
			
			//Formato de link video youtube src iframe: https://www.youtube.com/embed/h96Asp5BO2k //
			
			/*
			 * Lo mas recomendable de para subir videos de la url, es dejar modificado el iframe de youtube de esta manera:
			 * width="250" y height="200"
			 * Para ello, la ID de la url se debe copiar y pegar al después de "/embed/" que se encuentra en la url.
			 */
			
			String urlArchivoConIframe = "<iframe width=\'250\' height=\'200\' src=\'https://www.youtube.com/embed/" + url + "\' frameborder=\'0\' allowfullscreen></iframe>";
			
			boolean existVerifyVideo = dao.verifyVideoExist(urlArchivoConIframe);
			
			if(existVerifyVideo == true) {
				descripcion = "Video ya existe";
			} else {
				video.setIdVideo(dao.getMaxIdVideo());
				video.setNomVideo(request.getParameter("tituloVideo"));
				video.setUrlVideo(urlArchivoConIframe);
				video.setCategoria(request.getParameter("categoria"));
				dao.ingresarVideo(video);
				
				descripcion = "Video creado con éxito";
			}
			hasError = false;
			
			log.debug("Se ha llamado al Servlet mediante un proceso de tipo POST");
		} catch(Exception e) {
			hasError = true;
			descripcion = "Se produjo un error interno en el servidor al procesar la solicitud";
			System.out.println(e);
		}
		respuesta.put("hasError", hasError);
		respuesta.put("descripcion", descripcion);
		out.print(respuesta);
		out.flush();
	}

}
