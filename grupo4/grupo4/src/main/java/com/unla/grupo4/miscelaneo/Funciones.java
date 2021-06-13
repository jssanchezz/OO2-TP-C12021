package com.unla.grupo4.miscelaneo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Funciones {
	
	public static String pasarFechaAFormatoEuropeo(LocalDate fecha) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return fecha.format(formatter);
	}
	
	public static String cambiarEspaciosPorSignosMas(String str) {
		char[] ch = new char[str.length()];
		int i=0;
		
		for (i=0;i<str.length();i++) {
			if(str.charAt(i) != ' ') {
				ch[i] = str.charAt(i);
			}
			else {
				ch[i] = '+';
			}
		}
		
		return str.valueOf(ch);
	}
}
