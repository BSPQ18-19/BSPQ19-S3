package es.deusto.spq.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

import es.deusto.data.Pelicula;
import es.deusto.data.Perfil;
import es.deusto.data.Serie;

/**
 * La interface IRmi contiene todas las funciones remotas del servidor
 * 
 * @author Dani, Josu, Iker y Unai
 * @version 3.0
 * @since 2019-05-16
 *
 */
public interface IRmi extends Remote {

	/**
	 * Este metodo se utiliza para logear a los diferentes usuarios desde la BD.
	 * 
	 * @param usuario     Nombre de Usuario/Administrador deseado
	 * @param contrasenya Contraseña del Usuario/Administrador deseado
	 * @return boolean Devuelve True en caso de que haya encontrado un Usuario con
	 *         esa convinación de nombre y contraseña
	 * @exception RemoteException
	 * @see RemoteException
	 */
	public boolean login(String usuario, String contrasenya) throws RemoteException;

	/**
	 * Este metodo se utiliza para registrar un nuevo Usuario/Administrador en la
	 * BD.
	 * 
	 * @param usuario   Nombre de Usuario/Administrador deseado
	 * @param nick      Nick del Usuario/Administrador deseado
	 * @param pass      Contraseña del Usuario/Administrador deseado
	 * @param fecha_nac Fecha de nacimiento del Usuario/Administrador deseado
	 * @param tipo      Tipo de Uusaurio entre Usuario Estandar y Usuario
	 *                  Administrador
	 * @exception RemoteException
	 * @see RemoteException
	 */
	public void registrarse(String usuario, String nick, String pass, String fecha_nac, int tipo)
			throws RemoteException;

	/**
	 * Este metodo se utiliza para obtener una lista de perfiles del Usuario en
	 * cuestión BD.
	 * 
	 * @param usuario Nombre de Usuario del que se quieren obtener los perfiles.
	 * @exception RemoteException
	 * @see RemoteException
	 * @return List Devuelve una lista de Perfiles
	 */
	public Perfil[] getPerfiles(String usuario) throws RemoteException;

	/**
	 * Este metodo se utiliza para obtener el tipo de 
	 * Usuario(Estandar/Administrador) del Usuario en cuestión.
	 * 
	 * @param usuario Nombre de Usuario del que se quieren obtener el tipo.
	 * @exception RemoteException
	 * @see RemoteException
	 * @return boolean Devuelve True si El Usuario en de tipo Usuario
	 */
	public boolean getTipo(String usuario) throws RemoteException;

	/**
	 * Este metodo se utiliza para crear un nuevo Perfil para el Usuario en
	 * cuestión.
	 * 
	 * @param usuario Nombre de Usuario al que se quiere crear el nuevo Perfil.
	 * @param p  Perfil nuevo a crear
	 * @exception RemoteException
	 * @see RemoteException
	 */
	public void crearPerfil(String usuario, Perfil p) throws RemoteException;
	/**
	 * Este metodo se utiliza para obtener un listado de peliculas según el filtro
	 * 
	 * @param genero Género de la película que se desea buscar
	 * @param campoDeBusqueda Nombre de la Película que se desea buscar 
	 * @exception RemoteException
	 * @see RemoteException
	 * @return List Lista de peliculas
	 */
	public Pelicula[] buscarPelicula(String genero, String campoDeBusqueda) throws RemoteException;
	/**
	 * Este metodo se utiliza para obtener un listado de Series según el filtro
	 * 
	 * @param genero Género de la serie que se desea buscar
	 * @param campoDeBusqueda Nombre de la Serie que se desea buscar 
	 * @exception RemoteException
	 * @see RemoteException
	 * @return List Lista de Series
	 */
	public Serie[] buscarSerie(String genero, String campoDeBusqueda) throws RemoteException;
	/**
	 * Este metodo se utiliza para cambiar el control parental de un perfil
	 * 
	 * @param p Perfil al que se desea cambiar el control parental
	 * @exception RemoteException
	 * @see RemoteException
	 */
	public void cambiarControlParental(Perfil p) throws RemoteException;
	/**
	 * Este metodo se utiliza para obtener un listado de peliculas según el filtro(Administrador)
	 * 
	 * @param genero Género de la película que se desea buscar
	 * @param campoDeBusqueda Nombre de la Película que se desea buscar 
	 * @param modo String utilizado para poder obtener las peliculas sin nombre/sinopsis
	 * @exception RemoteException
	 * @see RemoteException
	 * @return List Lista de peliculas
	 */
	public Pelicula[] buscarPelicula(String genero, String campoDeBusqueda, String modo) throws RemoteException;
	/**
	 * Este metodo se utiliza para obtener un listado de Series según el filtro(Administrador)
	 * 
	 * @param genero Género de la serie que se desea buscar
	 * @param campoDeBusqueda Nombre de la Serie que se desea buscar
	 * @param modo  String utilizado para poder obtener las series sin nombre/sinopsis
	 * @exception RemoteException
	 * @see RemoteException
	 * @return List Lista de Series
	 */
	public Serie[] buscarSerie(String genero, String campoDeBusqueda, String modo) throws RemoteException;
	/**
	 * Este metodo se utiliza valorar una Película
	 * 
	 * @param val Valoración final de la Pelicula
	 * @param p Pelicula que se desea cambiar la valoración
	 * @exception RemoteException
	 * @see RemoteException
	 */
	public void valorarPelicula(double val, Pelicula p) throws RemoteException;
	/**
	 * Este metodo se utiliza valorar una Serie
	 * 
	 * @param val Valoración final de la Serie
	 * @param p Pelicula que se desea cambiar la valoración
	 * @exception RemoteException
	 * @see RemoteException
	 */

	public void valorarSerie(double val, Serie s) throws RemoteException;
}