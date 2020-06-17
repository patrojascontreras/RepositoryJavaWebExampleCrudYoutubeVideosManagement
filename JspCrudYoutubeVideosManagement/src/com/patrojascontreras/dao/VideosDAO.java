package com.patrojascontreras.dao;

import java.sql.*;
import java.util.*;
import com.patrojascontreras.dto.Video;
import com.patrojascontreras.util.Conexion;

public class VideosDAO {
	private static final long serialVersionUID = -8191660063912639643L;
	
	Conexion cn;
	static ResultSet rs = null;
	static Statement st = null;
	static PreparedStatement ps = null;
	String SQL = null;
	
	public VideosDAO() {
		cn = new Conexion();
	}
	
	public void ingresarVideo(Video video) {
		SQL = "INSERT into video SET id_video=?, titulo_video=?, video=?, categoria_video=?";
		
		try {
			Connection con = cn.getConexion();
			ps = con.prepareStatement(SQL);
			ps.setInt(1, video.getIdVideo());
			ps.setString(2, video.getNomVideo());
			ps.setString(3, video.getUrlVideo());
			ps.setString(4, video.getCategoria());
			ps.execute();
			ps.close();
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void modificarVideo(Video video) {
		SQL = "UPDATE video SET titulo_video=?, video=?, categoria_video=? WHERE id_video=?";
		
		try {
			Connection con = cn.getConexion();
			ps = con.prepareStatement(SQL);
			ps.setString(1, video.getNomVideo());
			ps.setString(2, video.getUrlVideo());
			ps.setString(3, video.getCategoria());
			ps.setInt(4, video.getIdVideo());
			ps.execute();
			ps.close();
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void eliminarVideo(int idVideo) {
		SQL = "DELETE FROM video WHERE id_video=?";
		
		try {
			Connection con = cn.getConexion();
			ps = con.prepareStatement(SQL);
			ps.setInt(1, idVideo);
			ps.execute();
			ps.close();
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public List<Video> listadoVideos() {
		List<Video> videos = new ArrayList<Video>();
		
		SQL = "SELECT video.id_video, video.titulo_video, video.video, categoria.categoria FROM video INNER JOIN categoria ON categoria.id_categoria=video.categoria_video";
		
		try {
			Connection con = cn.getConexion();
			st = con.createStatement();
			rs = st.executeQuery(SQL);
			
			while(rs.next()) {
				Video video = new Video();
				video.setIdVideo(rs.getInt("id_video"));
				video.setNomVideo(rs.getString("titulo_video"));
				video.setCategoria(rs.getString("categoria"));
				video.setUrlVideo(rs.getString("video"));
				videos.add(video);
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		return videos;
	}
	
	public List<Video> listadoVideosPorId(int idVideo) {
		List<Video> videos = new ArrayList<Video>();
		
		SQL = "SELECT video.id_video, video.titulo_video, video.video, categoria.categoria FROM video INNER JOIN categoria ON categoria.id_categoria=video.categoria_video WHERE id_video=?";
		
		try {
			Connection con = cn.getConexion();
			ps = con.prepareStatement(SQL);
			ps.setInt(1, idVideo);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Video video = new Video();
				video.setIdVideo(rs.getInt("id_video"));
				video.setNomVideo(rs.getString("titulo_video"));
				video.setCategoria(rs.getString("categoria"));
				video.setUrlVideo(rs.getString("video"));
				videos.add(video);
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		return videos;
	}
	
	public Video editarVideoPorId(int idVideo) {
		Video video = new Video();
		
		SQL = "SELECT * FROM video WHERE id_video=?";
		
		try {
			Connection con = cn.getConexion();
			ps = con.prepareStatement(SQL);
			ps.setInt(1, idVideo);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				video.setIdVideo(rs.getInt("id_video"));
				video.setNomVideo(rs.getString("titulo_video"));
				video.setCategoria(rs.getString("categoria_video"));
				video.setUrlVideo(rs.getString("video"));
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		return video;
	}
	
	public boolean confMostrarVideos() {
		boolean result = false;
		
		SQL = "SELECT video.id_video, video.titulo_video, video.video, categoria.categoria FROM video INNER JOIN categoria ON categoria.id_categoria=video.categoria_video";
		
		try {
			Connection con = cn.getConexion();
			st = con.createStatement();
			rs = st.executeQuery(SQL);
			
			if(!rs.next() || rs.getRow() == 0) {
				result = false;
			} else {
				result = true;
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		return result;
	}
	
	public boolean confMostrarVideosPorId(int idVideo) {
		boolean result = false;
		
		SQL = "SELECT video.id_video, video.titulo_video, video.video, categoria.categoria FROM video INNER JOIN categoria ON categoria.id_categoria=video.categoria_video WHERE id_video=?";
		
		try {
			Connection con = cn.getConexion();
			ps = con.prepareStatement(SQL);
			ps.setInt(1, idVideo);
			rs = ps.executeQuery();
			
			if(!rs.next() || rs.getRow() == 0) {
				result = false;
			} else {
				result = true;
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		return result;
	}
	
	public Video mostrarVideoPorIdPrevioEliminar(int idVideo) {
		Video video = new Video();
		
		SQL = "SELECT * FROM video WHERE id_video=?";
		
		try {
			Connection con = cn.getConexion();
			ps = con.prepareStatement(SQL);
			ps.setInt(1, idVideo);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				video.setIdVideo(rs.getInt("id_video"));
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		return video;
	}
	
	public int getMaxIdVideo() {
		int max = 0;
		
		SQL = "SELECT MAX(id_video) AS numero FROM video";
		
		try {
			Connection con = cn.getConexion();
			st = con.createStatement();
			rs = st.executeQuery(SQL);
			
			if(rs.next()) {
				max = rs.getInt("numero");
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		return (max + 1);
	}
	
	public boolean verifyVideoExist(String id) {
		boolean result = false;
		
		SQL = "SELECT video FROM video WHERE video=?";
		
		try {
			Connection con = cn.getConexion();
			ps = con.prepareStatement(SQL);
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) { 
				result = true;
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		return result;
	}
	
	public boolean existReadDataVideoId(int idVideo) {
		boolean result = false;
		
		SQL = "SELECT * FROM video WHERE id_video=?";
		
		try {
			Connection con = cn.getConexion();
			ps = con.prepareStatement(SQL);
			ps.setInt(1, idVideo);
			rs = ps.executeQuery();
			
			if(rs.next()) { 
				result = true;
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		return result;
	}
}
