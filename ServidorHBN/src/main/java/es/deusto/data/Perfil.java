package es.deusto.data;

import java.io.Serializable;

import javax.jdo.annotations.*;

@PersistenceCapable
public class Perfil implements Serializable{

  private static final long serialVersionUID = -4955550385639157495L;

  @PrimeryKey
  private String nombre;

  public Perfil(String nombre){
	  this.nombre= nombre;
  }

  public String getNombre(){
	  return nombre;
  }

  public void setNombre(String nombre){
	  this.nombre=nombre;
  }
}
