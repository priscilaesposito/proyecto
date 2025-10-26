package model;

import model.Usuario;

import java.sql.SQLException;

import DAO.DatosPersonalesDAO;
import DAOjdbc.DatosPersonalesDAOJdbc;

public class GestionUsuario {
    private DatosPersonalesDAO UDJ = new DAOjdbc.DatosPersonalesDAOJdbc();

    public void configurarIdioma() {
        // TODO: Implement configurarIdioma
    }

    public void configurarNombre() {
        // TODO: Implement configurarNombr
    }

    public void configurarPreferencias() {
        // TODO: Implement configurarPreferencias
    }

    private boolean dniUnico(int dni) throws SQLException {
        return UDJ.existeDNI(dni);
    }

    /**
     * Valida todos los campos de un objeto Usuario según los requisitos.
     * 
     * @param usuario El objeto Usuario con los datos a validar de datos personales.
     * @return El mismo objeto Usuario si es válido, o null si hay errores.
     */
    public static Usuario validacionDatos(Usuario usuario) {

        // Objeto para recolectar todos los errores encontrados
        StringBuilder errores = new StringBuilder();

        // 1. VERIFICAR NULIDAD DEL OBJETO
        if (usuario == null) {
            errores.append("Error fatal: El objeto Usuario es nulo.\n");
            System.out.println(errores.toString());
            return null;
        }

        if (usuario.getNombre() == null || usuario.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }

        if (!stringValido(usuario.getNombre())) {
            throw new IllegalArgumentException("El nombre no debe contener números ni caracteres especiales.");
        }

        if (usuario.getApellido() == null || usuario.getApellido().isEmpty()) { // Necesitas implementar getApellido()
                                                                                // en Usuario
            throw new IllegalArgumentException("El apellido no puede estar vacío.");
        }

        if (!stringValido(usuario.getApellido())) {
            throw new IllegalArgumentException("El apellido no debe contener números ni caracteres especiales.");
        }

        return;
    }

    public void registrarDatosPersonales(Usuario nuevoUsuario) throws Exception {
        UDJ.registrar(nuevoUsuario);
    }

    public boolean mailValido(String mail) {

        if (mail == null || mail.trim().isEmpty()) {
            return false;
        }

        int atIndex = mail.indexOf('@');

        if (atIndex <= 0) {
            return false;
        }

        if (mail.lastIndexOf('@') != atIndex) {
            return false;
        }

        if (atIndex == mail.length() - 1) {
            return false;
        }

        return true;

    }

    public void ValidacionUsuario(String nombreUsuario, String contrasenia, String mail) {

        if (nombreUsuario == null || nombreUsuario.trim().isEmpty()) {
            throw new IllegalArgumentException("El Nombre de Usuario no puede estar vacío.");
        }

        if (contrasenia == null || contrasenia.isEmpty()) {
            throw new IllegalArgumentException("La Contraseña no puede estar vacía.");
        }

        if (!mailValido(mail)) {
            throw new IllegalArgumentException("El formato del mail ingresado debe ser xxx@yyy.");
        }

        return;
    }

    public void suscribirse() {
        // TODO: Implement suscribirse
    }
}
