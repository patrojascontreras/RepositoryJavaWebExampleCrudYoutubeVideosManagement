package com.patrojascontreras.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class VideoServletIndex
 */
@WebServlet("/VideoServletIndex")
public class VideoServletIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(VideoServletIndex.class);
	
    public VideoServletIndex() {
        super();
    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("view/gestion_videos.jsp").forward(request, response);
		
		log.info("Se ha llamado al Servlet mediante un proceso de tipo GET");
	}

}
