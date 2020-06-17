package com.patrojascontreras.util;

import java.sql.*;

public class Conexion {
	private static final long serialVersionUID = -3929093755418286802L;
	
	static String bd = "jsp_youtube_videos_example";
	static String login = "root";
	static String password = "";
	static String url = "jdbc:mysql://localhost:3306/" + bd;

	Connection con = null;

	public Conexion() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection(url, login, password);

			if (con != null) {
				System.out.println("Conexión a Base de Datos " + bd + ". Listo");
			}
		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
	}

	public Connection getConexion() {
		return con;
	}

	public void desconectar() {
		con = null;
		System.out.println("La Conexión a la Base de Datos " + bd + " ha terminado");
	}
}
