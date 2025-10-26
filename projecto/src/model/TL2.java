package model;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import DAO.DatosPersonalesDAO;
import DAOjdbc.DatosPersonalesDAOJdbc;

import DAO.UsuarioDAO;
import DAOjdbc.UsuarioDAOjdbc;

public class TL2 {
	private UsuarioDAO UD = new DAOjdbc.UsuarioDAOjdbc();
	private DatosPersonalesDAO UDJ = new DAOjdbc.DatosPersonalesDAOJdbc();
	private LinkedList<Usuario> listaUsuarios;

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

	public LinkedList<Usuario> getListaUsuarios() {
		// Implementation needed
		return listaUsuarios;
	}

	public List<Usuario> getListaPersonas() throws SQLException {
		return UDJ.listarTodos();
	}

	public List<Usuario> getListaUsuariosDAO() throws SQLException {
		return UD.listarTodos();
	}

}
