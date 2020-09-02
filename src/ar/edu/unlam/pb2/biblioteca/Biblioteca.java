package ar.edu.unlam.pb2.biblioteca;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeSet;

public class Biblioteca {
     
	private String nombre;
	private Map<Integer, Libro> listaLibrosDeLaBiblioteca;
	private LinkedList<Alumno> listadeAlumnosRegistrados;
	private Integer cantidadDeLibrosDisponibleParaAlumnos;
	
	public Biblioteca(String nombre) {
		super();
		this.nombre = nombre;
		cantidadDeLibrosDisponibleParaAlumnos =3;
		listadeAlumnosRegistrados = new LinkedList<>();
		listaLibrosDeLaBiblioteca = new HashMap<>();
	}
    
	/*Metodo que registra un alumno  en el sistema , y lanza 
	 * una excepcion si el alumno ya se encuentra registrado*/
	public Boolean registrarAlumno(Alumno alumno) throws yaSeEncuentraRegistradaExcepcion{
		
		Boolean estado = false;
		
		if(listadeAlumnosRegistrados.contains(alumno) ){/*La clase persona lleva sobrescrito el metodo hashCode and equals en dni*/
			 
			throw new yaSeEncuentraRegistradaExcepcion();
			
		}else{
		listadeAlumnosRegistrados.add(alumno);
		 estado=true;
		}
		 return estado;
		
	}
	
	/*Metodo para registrar un libro en el sistema,
	 * Lanza una excepcion si el codigo con el cual se
	 * quiere registrar el libro  se encuentra utilizado*/
	
	public Boolean registroDeLibros(Libro libro) throws codigoNoDisponibleException{
		Boolean estadoDeRegistro=false;
		
		if(listaLibrosDeLaBiblioteca.containsKey(libro.getCodigo())){
			throw new codigoNoDisponibleException();
		} else{
		       listaLibrosDeLaBiblioteca.put(libro.getCodigo(), libro);
		       estadoDeRegistro = true;
		    }	
		return estadoDeRegistro;
	}
	
	/*Metodo que devuelve la cantidad de libros que otorgado a un alumno*/
	public Integer cantidadDeLibrosOtorgadosAUnALumno(Integer dni){
		return buscarAlumno(dni).getListaDeLibros().size();
	}
	
	/*Metodo que busca un libro por su nombre y retorna un objeto de tipo libro*/
	public Libro buscarLibro(String nombreDelLibro){
		 
		Libro estadoDeBusqueda = null;
		
		for(Libro libro : listaLibrosDeLaBiblioteca.values()){
			if(libro != null){
			if(libro.getNombreDelLibro().toLowerCase().equals(nombreDelLibro.toLowerCase())){
				
				estadoDeBusqueda = libro;
			}
		}
		}
		return estadoDeBusqueda;
	}
	/*Metodo que busca un alumno por su dni y retorna un objeto de tipo alumno*/
	private Alumno buscarAlumno(Integer dni){
		Alumno estadoDeBusqueda = null;
		
		for(Alumno alumno : listadeAlumnosRegistrados){
			if(alumno.getDni().equals(dni)){
				estadoDeBusqueda= alumno;
			}
		}
	return estadoDeBusqueda;
	}
	
	/*Metodo para prestar un libro ,lanza una excepcion si el alumno no es encontrado,
	 * lanza una excepcion si el libro no se encuentra disponible,
	 * lanza una excepcion si el alumno tiene el limite de cantidades de libro
	 * que puede sacar*/
	
	public Boolean prestarLibroAunAlumno(Integer dni,String nombreDelLibro) throws alumnoNoSeEncuentraRegistradoException, libroNoDisponibleException, noPuedeRetirarLibroException{
		
		Boolean estado = false;
		
		Alumno alumnoEncontrado= buscarAlumno(dni);
		Libro libroEncontrado= buscarLibro(nombreDelLibro);
		
		if(alumnoEncontrado== null){
			throw new alumnoNoSeEncuentraRegistradoException();
		}
		if(libroEncontrado == null){
			throw new libroNoDisponibleException();
		}
		if(cantidadDeLibrosOtorgadosAUnALumno(dni)>= cantidadDeLibrosDisponibleParaAlumnos){
			throw new noPuedeRetirarLibroException();
		}else{
		listaLibrosDeLaBiblioteca.put(libroEncontrado.getCodigo(), null);/*Cuando un libro es prestado se conserva el codigo del libro, 
		                                                     pero se le otorga un null al objeto*/
		
		alumnoEncontrado.getListaDeLibros().add(libroEncontrado);/*El libro encontrado se agrega en la lista de libros del alumno*/
		estado = true;
		}
		return estado;
	}
	/*Metodo que sirve para que un alumno devuelva un libro*/
	public boolean devolverLibro(Integer dni,String nombreDelLibro){
		
		Boolean estado=false;
		Libro libroADevolver = null;
		Alumno alumnoEncontrado = buscarAlumno(dni);/*Se busca al alumno en la lista de alumnos registrados de la biblioteca*/
		
		for(Libro libro : alumnoEncontrado.getListaDeLibros()){/*Se busca en la lista de libros del alumno,el libro que va a devolver*/
			if(libro.getNombreDelLibro().toLowerCase().equals(nombreDelLibro.toLowerCase())){

				libroADevolver = libro;
				estado=true;
				
			}
		}
		
		listaLibrosDeLaBiblioteca.put(libroADevolver.getCodigo(), libroADevolver);/*Se duelve el libro en la lista de libros de la biblioteca*/
		alumnoEncontrado.getListaDeLibros().remove(libroADevolver);/*Se elimina de la lista de libros del alumno,el libro que devolvio*/
		
		return estado;
	}
	
	/*Metodo que devuelve una lista con todos los libros de un mismo autor
	 * que contiene la lista de libros de la biblioteca*/
	
	public ArrayList<Libro> buscarTodosLosLibrosDeUnAutor(String nombreDelAutor){
		
		ArrayList<Libro> listaDeLibrosUnAutor = new ArrayList<>();
		
		  for(Libro libro : listaLibrosDeLaBiblioteca.values()){
			  if(libro.getAutor().toLowerCase().equals(nombreDelAutor.toLowerCase())){
				  listaDeLibrosUnAutor.add(libro);
			  }
		  }
		
		return listaDeLibrosUnAutor;
	}
	
	/*Metodo que devuelve una lista de libros ordenados por nombre de autor*/
	public TreeSet<Libro> listaDeLibrosOrdenadaPorAutor(){
	
	TreeSet<Libro> listaDeLibrosOrdenadaPorAutor = new TreeSet<>();
	listaDeLibrosOrdenadaPorAutor.addAll(listaLibrosDeLaBiblioteca.values());
	return listaDeLibrosOrdenadaPorAutor;
	}
    /*Metodo que devuelve una lista de libros ordenados por su nombre*/
	public TreeSet<Libro> listaDeLibrosOrdenadaPornombre(){
		
		TreeSet<Libro> listaDeLibrosOrdenadaPornombre = new TreeSet<>(new libroOrdenadoPorNombre());
		listaDeLibrosOrdenadaPornombre.addAll(listaLibrosDeLaBiblioteca.values());
		return listaDeLibrosOrdenadaPornombre;
		
	}
	
	public String getNombre() {
		return nombre;
	}

	public Map<Integer, Libro> getListaLibros() {
		return listaLibrosDeLaBiblioteca;
	}

	public LinkedList<Alumno> getListadeAlumnos() {
		return listadeAlumnosRegistrados;
	}


	public Integer getCantidadDeLibrosDisponibleParaAlumnos() {
		return cantidadDeLibrosDisponibleParaAlumnos;
	}

	public void setCantidadDeLibrosDisponibleParaAlumnos(Integer cantidadDeLibrosDisponibleParaAlumnos) {
		this.cantidadDeLibrosDisponibleParaAlumnos = cantidadDeLibrosDisponibleParaAlumnos;
	}
	 
	
	
	
}
