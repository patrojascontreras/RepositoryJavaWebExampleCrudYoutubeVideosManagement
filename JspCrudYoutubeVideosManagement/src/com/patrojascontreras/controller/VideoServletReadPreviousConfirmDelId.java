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

@WebServlet("/VideoServletReadPreviousConfirmDelId")
public class VideoServletReadPreviousConfirmDelId extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(VideoServletReadPreviousConfirmDelId.class.getName());
	
	private VideosDAO dao;
	
    public VideoServletReadPreviousConfirmDelId() {
        super();
        dao = new VideosDAO();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		JSONObject respuesta = new JSONObject();
		
		try {
			int idVideo = Integer.parseInt(request.getParameter("idVideoConfirmDel"));
			int idVideo1;
			
			Video videoReadPrevConfDel = dao.editarVideoPorId(idVideo);
			
			idVideo1 = videoReadPrevConfDel.getIdVideo();
			
			respuesta.put("idVideo1", idVideo1);
		} catch(Exception e) {
			System.out.println(e);
		}
		out.print(respuesta);
		out.flush();
		
		log.debug("Se ha llamado al Servlet mediante un proceso de tipo GET");
	}

}
