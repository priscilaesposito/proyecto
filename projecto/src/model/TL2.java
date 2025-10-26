package model;

import java.util.LinkedList;

import DAO.DatosPersonalesDAO;
import DAOjdbc.DatosPersonalesDAOJdbc;


public class TL2 {
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

	public LinkedList<Usuario> getListaPersonas() {
		
        return listaUsuarios;
	}

}
