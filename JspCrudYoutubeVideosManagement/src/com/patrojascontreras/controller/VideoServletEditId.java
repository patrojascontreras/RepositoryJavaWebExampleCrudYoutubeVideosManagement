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

@WebServlet("/VideoServletEditId")
public class VideoServletEditId extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(VideoServletEditId.class.getName());
	
	private VideosDAO dao;
	
    public VideoServletEditId() {
        super();
        dao = new VideosDAO();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		JSONObject respuesta = new JSONObject();
		
		try {
			int idVideo = Integer.parseInt(request.getParameter("idVideoEdit"));
			String videoTitulo;
			String urlVideo;
			String categoria;
			int idVideo1;
			
			Video videoReadId = dao.editarVideoPorId(idVideo);
			
			videoTitulo = videoReadId.getNomVideo();
			urlVideo = videoReadId.getUrlVideo();
			categoria = videoReadId.getCategoria();
			idVideo1 = videoReadId.getIdVideo();
			
			respuesta.put("videoTitulo", videoTitulo);
			respuesta.put("urlVideo", urlVideo);
			respuesta.put("categoria", categoria);
			respuesta.put("idVideo1", idVideo1);
		} catch(Exception e) {
			System.out.println(e);
		}
		out.print(respuesta);
		out.flush();
		
		log.debug("Se ha llamado al Servlet mediante un proceso de tipo GET");
	}

}
