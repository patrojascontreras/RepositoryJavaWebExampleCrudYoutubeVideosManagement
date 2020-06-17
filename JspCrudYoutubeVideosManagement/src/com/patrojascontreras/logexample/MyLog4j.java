package com.patrojascontreras.logexample;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class MyLog4j {
	
	public static void main(String[] args) {
		//Cargar archivo de configuración
		PropertyConfigurator.configure("/WEB-INF/log4j.properties");
		
		//Obtener Instancia de Logger
		Logger log = Logger.getLogger(MyLog4j.class);
		
		//Escribir algunos logs en diferentes etapas
		log.debug("Prueba en proceso DEBUG");
		log.info("Prueba en proceso INFO");
		log.warn("Prueba en proceso WARNING");
		log.error("Prueba en proceso ERROR");
		log.fatal("Prueba en proceso FATAL");
	}
}
