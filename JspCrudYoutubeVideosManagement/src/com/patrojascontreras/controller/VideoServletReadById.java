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

import com.patrojascontreras.dto.Video;
import com.patrojascontreras.dao.VideosDAO;

/**
 * Servlet implementation class VideoServletReadById
 */
@WebServlet("/VideoServletReadById")
public class VideoServletReadById extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(VideoServletReadById.class.getName());
	
	private VideosDAO dao;
	
    public VideoServletReadById() {
        super();
        dao = new VideosDAO();
    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		int buscarIdVideo = Integer.parseInt(request.getParameter("buscarPorId"));
		
		List<Video> listadoVideosPorId = dao.listadoVideosPorId(buscarIdVideo);
		boolean errorConfMostrarVideosPorId = dao.confMostrarVideosPorId(buscarIdVideo);
		
		out.print("<table class=\"table table-bordered\">");
		out.print("   <thead>\n"
				+ "        <tr>\n"
				+ "            <td align=\"center\">ID</td>\n"
				+ "            <td>Título</td>\n"
				+ "            <td>Categoria</td>\n"
				+ "            <td align=\"center\">Video</td>\n"
				+ "            <td align=\"center\">Acciones</td>\n"
				+ "        </tr>\n"
				+ "   </thead>\n"
				+ "   <tbody>");
		
		if(errorConfMostrarVideosPorId == false) {
			out.print("   <tr>\n"
					+ "      <td colspan=\"5\" align=\"center\">No hay resultados</td>\n"
					+ "   </tr>");
		} else {
			for(Video video : listadoVideosPorId) {
				int idVideo = video.getIdVideo();
				String videoTitulo = video.getNomVideo();
				String categoria = video.getCategoria();
				String urlVideo = video.getUrlVideo();
				
				out.print("<tr>\n"
						+ "    <td align=\"center\">" + idVideo + "</td>\n"
						+ "    <td>" + videoTitulo + "</td>\n"
						+ "    <td>" + categoria + "</td>\n"
						+ "    <td align=\"center\">" + urlVideo + "</td>\n"
						+ "    <td align=\"center\">\n"
						+ "              <a href=\"javascript:editarVideo(" + idVideo + ");\"><img src=\"resources/images/edit_image.png\" /></a>\n"
						+ "              <a href=\"javascript:mostrarModalConfirmEliminarVideo(" + idVideo + ");\"><img src=\"resources/images/delete_image.png\" /></a>\n"
						+ "    </td>\n"
						+ "</tr>");
			}
		}
		out.print("   </tbody>");
		out.print("</table>");
		
		log.debug("Se ha llamado al Servlet mediante un proceso de tipo GET");
	}

}
