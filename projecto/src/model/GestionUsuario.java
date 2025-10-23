package model;

import model.Usuario;

import UsuarioDAOjdbc;

public class GestionUsuario {

	public void configurarIdioma() {
		// TODO: Implement configurarIdioma
	}
	
	public void configurarNombre() {
		// TODO: Implement configurarNombre
	}
	
	public void configurarPreferencias() {
		// TODO: Implement configurarPreferencias
	}

	public boolean dniUnico(int dni){
		DAOjdbc.UsuarioDAOjdbc UDJ= new DAOjdbc.UsuarioDAOjdbc();
        return UDJ.existeDNI(dni);
	}
	
	public Usuario registrarse(Usuario usuario) {
		

		return usuario;
	}
	
	public void suscribirse() {
		// TODO: Implement suscribirse
	}
}
