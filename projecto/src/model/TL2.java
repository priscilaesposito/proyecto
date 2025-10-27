package model;

import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import DAO.DatosPersonalesDAO;
import DAOjdbc.DatosPersonalesDAOJdbc;

import DAO.UsuarioDAO;
import DAOjdbc.UsuarioDAOjdbc;

import DAO.PeliculaDAO;
import DAOjdbc.PeliculaDAOjdbc;

import Utilidades.OrdenarPorNombreUsuario;
import Utilidades.OrdenarPorMail;

import Utilidades.OrdenarPorTitulo;
import Utilidades.OrdenarPorDuracion;
import Utilidades.OrdenarPorPrimerGenero;

public class TL2 {
	private PeliculaDAO PD = new DAOjdbc.PeliculaDAOjdbc();
	private UsuarioDAO UD = new DAOjdbc.UsuarioDAOjdbc();
	private DatosPersonalesDAO UDJ = new DAOjdbc.DatosPersonalesDAOJdbc();
	private LinkedList<Usuario> listaUsuarios;
	private LinkedList<Titulo> catalogo;

	public String iniciarSesion(String username, String password) {
		// Implementation needed
		return null;
	}

	public boolean autenticacion(String token) {
		// Implementation needed
		return false;
	}

	public LinkedList<Titulo> getCatalogo() {
		// Implementation needed
		return null;
	}

	private void restringirGeolocalizacionCatalogo() {
		// Implementation needed
	}

	public LinkedList<Usuario> getListaUsuarios() throws SQLException {

		return listaUsuarios;
	}

	public List<Usuario> getListaPersonas() throws SQLException {
		return UDJ.listarTodos();
	}

	public List<Usuario> getListaUsuariosDAO() throws SQLException {
		return UD.listarTodos();
	}

	public List<Usuario> listarUsuariosOrdenados(String criterio) throws SQLException {

		List<Usuario> listaUsuarios = UD.listarTodos();

		if ("NOMBRE".equalsIgnoreCase(criterio)) {
			Collections.sort(listaUsuarios, new OrdenarPorNombreUsuario());

		} else if ("EMAIL".equalsIgnoreCase(criterio)) {
			Collections.sort(listaUsuarios, new OrdenarPorMail());

		} else {
			System.out.println("Criterio de ordenación no válido. Se muestra sin ordenar.");
		}

		return listaUsuarios;
	}

	public List<Pelicula> listarPeliculasOrdenadas(String criterio) {

		List<Pelicula> listaPeliculas = PD.listarTodos();

		if ("TITULO".equalsIgnoreCase(criterio)) {
			Collections.sort(listaPeliculas, new OrdenarPorTitulo());
		} else if ("DURACION".equalsIgnoreCase(criterio)) {
			Collections.sort(listaPeliculas, new OrdenarPorDuracion());
		} else if ("GENERO".equalsIgnoreCase(criterio)) {
			Collections.sort(listaPeliculas, new OrdenarPorPrimerGenero());
		} else {
			System.out.println("Criterio de ordenación no válido. Se muestra sin ordenar.");
		}

		return listaPeliculas;
	}

}
