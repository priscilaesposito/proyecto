package model;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import DAO.DatosPersonalesDAO;
import DAOjdbc.DatosPersonalesDAOjdbc;


public class TL2 {
	private DatosPersonalesDAO UDJ = new DAOjdbc.DatosPersonalesDAOjdbc();
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

}
