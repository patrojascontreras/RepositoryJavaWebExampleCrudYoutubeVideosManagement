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
 * Servlet implementation class VideoServletUpdate
 */
@WebServlet("/VideoServletUpdate")
public class VideoServletUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(VideoServletUpdate.class.getName());
	
	private VideosDAO dao;
	
	private boolean hasError = false;
	
    public VideoServletUpdate() {
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
			
			video.setNomVideo(request.getParameter("tituloVideoEdit"));
			video.setCategoria(request.getParameter("categoriaEdit"));
			
			int idVideo = Integer.parseInt(request.getParameter("idVideoEdit"));
			
			String descripcionUpdate = "Video modificado con éxito";
			
			if(request.getParameter("urlVideoEdit") != null) {
				String urlId = request.getParameter("urlVideoEdit");
				String urlArchivoConIframe = "<iframe width=\'250\' height=\'200\' src=\'https://www.youtube.com/embed/" + urlId + "\' frameborder=\'0\' allowfullscreen></iframe>";
				
				boolean existVerifyVideo = dao.verifyVideoExist(urlArchivoConIframe);
				
				if(existVerifyVideo == true) {
					descripcion = "Video ya existe";
				} else {
					video.setUrlVideo(urlArchivoConIframe);
					video.setIdVideo(idVideo);
					
					dao.modificarVideo(video);
					descripcion = descripcionUpdate;
				}
			} else {
				Video videoReadId = dao.editarVideoPorId(idVideo);
				String videoUrlRead = videoReadId.getUrlVideo();
				
				video.setUrlVideo(videoUrlRead);
				video.setIdVideo(idVideo);
				
				dao.modificarVideo(video);
				descripcion = descripcionUpdate;
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
