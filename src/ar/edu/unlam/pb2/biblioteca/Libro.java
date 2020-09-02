package ar.edu.unlam.pb2.biblioteca;

public class Libro implements Comparable<Libro> {
  
	private String nombreDelLibro;
	private String autor;
	private Integer codigo;
	private String descripcion;
	
	
	public Libro(String nombreDelLibro, String autor, Integer codigo, String descripcion) {
		super();
		this.setNombreDelLibro(nombreDelLibro);
		this.autor = autor;
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getAutor() {
		return autor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getNombreDelLibro() {
		return nombreDelLibro;
	}

	public void setNombreDelLibro(String nombreDelLibro) {
		this.nombreDelLibro = nombreDelLibro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public int compareTo(Libro arg0) {
		// TODO Auto-generated method stub
		return this.autor.compareTo(arg0.getAutor());
	}
	
	
	
}
