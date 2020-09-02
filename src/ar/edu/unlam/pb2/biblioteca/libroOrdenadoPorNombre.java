package ar.edu.unlam.pb2.biblioteca;

import java.util.Comparator;

public class libroOrdenadoPorNombre implements Comparator<Libro> {

	@Override
	public int compare(Libro arg0, Libro arg1) {
		// TODO Auto-generated method stub
		return arg0.getNombreDelLibro().compareTo(arg1.getNombreDelLibro());
	}

}
