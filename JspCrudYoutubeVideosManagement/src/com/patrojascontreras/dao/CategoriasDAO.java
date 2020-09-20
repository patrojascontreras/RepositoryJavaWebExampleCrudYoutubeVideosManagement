package com.patrojascontreras.dao;

import java.sql.*;
import java.util.*;
import com.patrojascontreras.dto.Categoria;
import com.patrojascontreras.util.Conexion;

public class CategoriasDAO {
	private static final long serialVersionUID = 2952788846450932683L;
	
	Conexion cn;
	static ResultSet rs = null;
	static Statement st = null;
	private List<Categoria> categoriaList;
	String SQL = null;
	
	public CategoriasDAO() {
		cn = new Conexion();
	}
	
	public List<Categoria> listadoCategorias() {
		categoriaList = new ArrayList<Categoria>();
		
		SQL = "SELECT * FROM categoria";
		
		try {
			Connection con = cn.getConexion();
			st = con.createStatement();
			rs = st.executeQuery(SQL);
			
			while(rs.next()) {
				int idCategoria = rs.getInt("id_categoria");
				String categoria = rs.getString("categoria");
				Categoria cat = new Categoria(idCategoria, categoria);
				categoriaList.add(cat);
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		return categoriaList;
	}
}
