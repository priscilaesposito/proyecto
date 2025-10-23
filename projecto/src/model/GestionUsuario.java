package model;

import model.Usuario;
import DAO.DatosPersonalesDAO;
import DAOjdbc.DatosPersonalesDAOjdbc;

public class GestionUsuario {
    private DatosPersonalesDAO UDJ = new DAOjdbc.DatosPersonalesDAOjdbc();

	public void configurarIdioma() {
		// TODO: Implement configurarIdioma
	}
	
	public void configurarNombre() {
		// TODO: Implement configurarNombre
	}
	
	public void configurarPreferencias() {
		// TODO: Implement configurarPreferencias
	}

	private boolean dniUnico(int dni){
        return UDJ.existeDNI(dni);
	}
	
	public Usuario registrarse(Usuario usuario) {
		
		return usuario;
	}
	
	public void suscribirse() {
		// TODO: Implement suscribirse
	}
}
