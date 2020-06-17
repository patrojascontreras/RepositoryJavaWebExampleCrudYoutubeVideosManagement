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

import com.patrojascontreras.dao.VideosDAO;

/**
 * Servlet implementation class VideoServletDelete
 */
@WebServlet("/VideoServletDelete")
public class VideoServletDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(VideoServletDelete.class.getName());
	
	private VideosDAO dao;
	
	private boolean hasError = false;
	
    public VideoServletDelete() {
        super();
        dao = new VideosDAO();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		JSONObject respuesta = new JSONObject();
		
		String descripcion = "";
		
		int idVideo = Integer.parseInt(request.getParameter("idVideoConfirmDel"));
		
		try {
			
			boolean existReadDataVideoId = dao.existReadDataVideoId(idVideo);
			
			if(existReadDataVideoId == false) {
				descripcion = "Video no encontrado";
			} else {
				dao.eliminarVideo(idVideo);
				descripcion = "Video eliminado correctamente";
			}
			
			hasError = false;
			
			log.debug("Se ha llamado al Servlet mediante un proceso de tipo GET");
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
