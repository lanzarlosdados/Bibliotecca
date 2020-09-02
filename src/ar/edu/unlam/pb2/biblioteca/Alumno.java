package ar.edu.unlam.pb2.biblioteca;

import java.util.ArrayList;

public class Alumno extends Persona {

	
	private ArrayList<Libro> listaDeLibros;
	
	public Alumno(String nombre, String apellido, Integer dni) {
		super(nombre, apellido, dni);
		// TODO Auto-generated constructor stub
		listaDeLibros = new ArrayList<>();
	}


	public ArrayList<Libro> getListaDeLibros() {
		return listaDeLibros;
	}
	
	

}
