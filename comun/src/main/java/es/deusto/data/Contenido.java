package es.deusto.data;

import javax.swing.ImageIcon;

public interface Contenido {
	
//	private ImageIcon portada;
//	private String titulo;
//	
//	public Contenido() {
//		super();
//	}
//	public Contenido(ImageIcon portada, String titulo) {
//		super();
//		this.portada = portada;
//		this.titulo = titulo;
//	}
//	public ImageIcon getPortada() {
//		return portada;
//	}
//	public void setPortada(ImageIcon portada) {
//		this.portada = portada;
//	}
//	public String getTitulo() {
//		return titulo;
//	}
//	public void setTitulo(String titulo) {
//		this.titulo = titulo;
//	}
//	@Override
//	public String toString() {
//		return "Contenido [portada=" + portada + ", titulo=" + titulo + "]";
//	}
//	
	public ImageIcon getPortada();
	public void setPortada(ImageIcon portada);
	public String getTitulo();
	public void setTitulo(String titulo);
	
}
