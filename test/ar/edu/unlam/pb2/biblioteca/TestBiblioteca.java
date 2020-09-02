package ar.edu.unlam.pb2.biblioteca;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;




public class TestBiblioteca {
	
	
	private Biblioteca miBiblioteca ;
	
	
	@Before
    public void init(){
       miBiblioteca = new Biblioteca("Biblioteca Unlam");
    }
	@Test
	public void testQueRegistreUnAlumno() throws yaSeEncuentraRegistradaExcepcion{
		
		assertTrue(miBiblioteca.registrarAlumno(new Alumno("Fabian", "Zarate", 34539528)));
	}
	
	@Test (expected = yaSeEncuentraRegistradaExcepcion.class)
	public void tesQueNoAgregueUnAlumnoYaRegistrado() throws yaSeEncuentraRegistradaExcepcion{
		miBiblioteca.registrarAlumno(new Alumno("Fabian", "Zarate", 34539528));
		miBiblioteca.registrarAlumno(new Alumno("Fabian", "Zarate", 34539528));
	}
	@Test
	public void testQueRegistreUnLibro() throws codigoNoDisponibleException{
		
		assertTrue(miBiblioteca.registroDeLibros(new Libro("Filosofia", "Dario", 1234, "Filosofia a Martillazos")));
	}
	@Test (expected = codigoNoDisponibleException.class)
	public void testQueNoAgregarUnLibroYaRegistrado() throws codigoNoDisponibleException{
		miBiblioteca.registroDeLibros(new Libro("Filosofia", "Dario", 1234, "Filosofia a Martillazos"));
		miBiblioteca.registroDeLibros(new Libro("Filosofia", "Dario", 1234, "Filosofia a Martillazos"));
	}
	
	@Test
	public void testQuePresteUnLibroAUnAlumno() throws yaSeEncuentraRegistradaExcepcion, codigoNoDisponibleException, alumnoNoSeEncuentraRegistradoException, libroNoDisponibleException, noPuedeRetirarLibroException{
		miBiblioteca.registrarAlumno(new Alumno("Fabian", "Zarate", 34539528));
		miBiblioteca.registroDeLibros(new Libro("Filosofia", "Dario", 1234, "Filosofia a Martillazos"));
		
		assertTrue(miBiblioteca.prestarLibroAunAlumno(34539528, "filosofia"));
		
		
	}
	@Test(expected = libroNoDisponibleException.class)
	public void testQueLanzaUnaExcepcionSiElLibroAPrestarNoSeEncuentra() throws yaSeEncuentraRegistradaExcepcion, codigoNoDisponibleException, alumnoNoSeEncuentraRegistradoException, libroNoDisponibleException, noPuedeRetirarLibroException{
		miBiblioteca.registrarAlumno(new Alumno("Fabian", "Zarate", 34539528));
		miBiblioteca.registroDeLibros(new Libro("Filosofia", "Dario", 1234, "Filosofia a Martillazos"));
		miBiblioteca.prestarLibroAunAlumno(34539528, "Historia");
		
	}
	
	@Test (expected = alumnoNoSeEncuentraRegistradoException.class)
	public void testQueLanzaUnaExcepcionSiElAlumnoNoSeEncuentraRegistrado() throws yaSeEncuentraRegistradaExcepcion, codigoNoDisponibleException, alumnoNoSeEncuentraRegistradoException, libroNoDisponibleException, noPuedeRetirarLibroException{
		miBiblioteca.registrarAlumno(new Alumno("Fabian", "Zarate", 34539528));
		miBiblioteca.registroDeLibros(new Libro("Filosofia", "Dario", 1234, "Filosofia a Martillazos"));
		miBiblioteca.prestarLibroAunAlumno(34539527, "Filosfia");
		
	}
	
	@Test (expected = noPuedeRetirarLibroException.class)
	public void testQueLanzaUnaExcepcionCuandoElAlumnoNoPuedeRetirarLibros() throws yaSeEncuentraRegistradaExcepcion, codigoNoDisponibleException, alumnoNoSeEncuentraRegistradoException, libroNoDisponibleException, noPuedeRetirarLibroException{
		miBiblioteca.registrarAlumno(new Alumno("Fabian", "Zarate", 34539528));
		
		miBiblioteca.registroDeLibros(new Libro("Filosofia", "Dario", 1234, "Filosofia a Martillazos"));
		miBiblioteca.registroDeLibros(new Libro("Historia", "Hugo", 1235, "descripcion"));
		miBiblioteca.registroDeLibros(new Libro("Ciencias", "fabian", 1236, "descripcion"));
		miBiblioteca.registroDeLibros(new Libro("Matematica", "Alejandro", 1238, "descripcion"));
		
		miBiblioteca.prestarLibroAunAlumno(34539528, "Filosofia");
		miBiblioteca.prestarLibroAunAlumno(34539528, "Historia");
		miBiblioteca.prestarLibroAunAlumno(34539528, "Ciencias");
		miBiblioteca.prestarLibroAunAlumno(34539528, "Matematica");
	}
	@Test
	public void testQueEncuentreUnLibro() throws  codigoNoDisponibleException{
		
		miBiblioteca.registroDeLibros(new Libro("Filosofia", "Dario", 1234, "Filosofia a Martillazos"));
		miBiblioteca.registroDeLibros(new Libro("Historia", "Hugo", 1235, "descripcion"));
		miBiblioteca.registroDeLibros(new Libro("Ciencias", "fabian", 1236, "descripcion"));
		miBiblioteca.registroDeLibros(new Libro("Matematica", "Alejandro", 1238, "descripcion"));
		
		assertEquals(miBiblioteca.buscarLibro("Filosofia"),new Libro("Filosofia", "Dario", 1234, "Filosofia a Martillazos") );
	}
	@Test
	public void testQueNoEncuentreUnLibro() throws codigoNoDisponibleException{
		
		miBiblioteca.registroDeLibros(new Libro("Filosofia", "Dario", 1234, "Filosofia a Martillazos"));
		miBiblioteca.registroDeLibros(new Libro("Historia", "Hugo", 1235, "descripcion"));
		miBiblioteca.registroDeLibros(new Libro("Ciencias", "fabian", 1236, "descripcion"));
		miBiblioteca.registroDeLibros(new Libro("Matematica", "Alejandro", 1238, "descripcion"));
		
		assertNull(miBiblioteca.buscarLibro("Jardineria"));
	}
	@Test
	public void testqueDevuelvaUnLibro() throws yaSeEncuentraRegistradaExcepcion, codigoNoDisponibleException, alumnoNoSeEncuentraRegistradoException, libroNoDisponibleException, noPuedeRetirarLibroException{
		miBiblioteca.registrarAlumno(new Alumno("Fabian", "Zarate", 34539528));
		
		miBiblioteca.registroDeLibros(new Libro("Filosofia", "Dario", 1234, "Filosofia a Martillazos"));
		miBiblioteca.registroDeLibros(new Libro("Historia", "Hugo", 1235, "descripcion"));
		miBiblioteca.registroDeLibros(new Libro("Ciencias", "fabian", 1236, "descripcion"));
		miBiblioteca.registroDeLibros(new Libro("Matematica", "Alejandro", 1238, "descripcion"));
		
		miBiblioteca.prestarLibroAunAlumno(34539528, "Filosofia");
		miBiblioteca.prestarLibroAunAlumno(34539528, "Historia");
		miBiblioteca.prestarLibroAunAlumno(34539528, "Ciencias");
		
		assertTrue(miBiblioteca.devolverLibro(34539528, "Filosofia"));
	}
	@Test
	public void testQueDevuelvaLaCantidadCorrectaQueTieneUnAlumno() throws yaSeEncuentraRegistradaExcepcion, codigoNoDisponibleException, alumnoNoSeEncuentraRegistradoException, libroNoDisponibleException, noPuedeRetirarLibroException{
		
		miBiblioteca.registrarAlumno(new Alumno("Fabian", "Zarate", 34539528));
		
		miBiblioteca.registroDeLibros(new Libro("Filosofia", "Dario", 1234, "Filosofia a Martillazos"));
		miBiblioteca.registroDeLibros(new Libro("Historia", "Hugo", 1235, "descripcion"));
		miBiblioteca.registroDeLibros(new Libro("Ciencias", "fabian", 1236, "descripcion"));
		miBiblioteca.registroDeLibros(new Libro("Matematica", "Alejandro", 1238, "descripcion"));
		
		miBiblioteca.prestarLibroAunAlumno(34539528, "Filosofia");
		miBiblioteca.prestarLibroAunAlumno(34539528, "Historia");
		miBiblioteca.prestarLibroAunAlumno(34539528, "Ciencias");
		
		miBiblioteca.devolverLibro(34539528, "Filosofia");
		
		Integer expected = 2;
		Integer actual = miBiblioteca.cantidadDeLibrosOtorgadosAUnALumno(34539528);
		assertEquals(expected, actual);
		
		
	}
	@Test
	public void testQueDevuelvaUnaListaDeTodosLosLibroDeUnMismoAutor() throws codigoNoDisponibleException{

		
		miBiblioteca.registroDeLibros(new Libro("Filosofia", "Dario", 1234, "Filosofia a Martillazos"));
		miBiblioteca.registroDeLibros(new Libro("Historia", "dario", 1235, "descripcion"));
		miBiblioteca.registroDeLibros(new Libro("Ciencias", "dario", 1236, "descripcion"));
		miBiblioteca.registroDeLibros(new Libro("Matematica", "dario", 1238, "descripcion"));
		
		Integer actual = miBiblioteca.buscarTodosLosLibrosDeUnAutor("dario").size();
		Integer expected =4;
		
		assertEquals(expected, actual);
		
	}
	@Test
	public void testQueDevuelvaListaOrdenadaPorNombreDeAutor() throws codigoNoDisponibleException{
		miBiblioteca.registroDeLibros(new Libro("Filosofia", "alejandro", 1234, "Filosofia a Martillazos"));
		miBiblioteca.registroDeLibros(new Libro("Historia", "belen", 1235, "descripcion"));
		miBiblioteca.registroDeLibros(new Libro("Ciencias", "calamaro", 1236, "descripcion"));
		
		
        int i = 0;
        for(Libro libro : miBiblioteca.listaDeLibrosOrdenadaPorAutor()){
            
            switch (i){
            case 0:
                assertTrue("alejandro".equals(libro.getAutor()));
                break;
            case 1:
                assertTrue("belen".equals(libro.getAutor()));
                break;
            case 2:
                assertTrue("calamaro".equals(libro.getAutor()));
                break;
            }
            i++;
        }
		
	}
	@Test
	public void testQueDevuelvaListaOrdenadaPorNombreDeLibro() throws codigoNoDisponibleException{
		miBiblioteca.registroDeLibros(new Libro("Filosofia", "alejandro", 1234, "Filosofia a Martillazos"));
		miBiblioteca.registroDeLibros(new Libro("Historia", "belen", 1235, "descripcion"));
		miBiblioteca.registroDeLibros(new Libro("Ciencias", "calamaro", 1236, "descripcion"));
		
		
        int i = 0;
        for(Libro libro : miBiblioteca.listaDeLibrosOrdenadaPornombre()){
            
            switch (i){
            case 0:
                assertTrue("ciencias".equalsIgnoreCase(libro.getNombreDelLibro()));
                break;
            case 1:
                assertTrue("filosofia".equalsIgnoreCase(libro.getNombreDelLibro()));
                break;
            case 2:
            	
                assertTrue("Historia".equals(libro.getNombreDelLibro()));
                break;
            }
            i++;
        }
		
	}
}
