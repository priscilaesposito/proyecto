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
        // TODO: Implement configurarNombre
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

        if (errores.length() > 0) {
            System.out.println(" --- ERRORES DE VALIDACIÓN ENCONTRADOS ---");
            System.out.println("El sistema informa los siguientes datos erróneos:");
            System.out.println(errores.toString());
            return null; // Devuelve null para indicar que el usuario no es válido
        } else {
            System.out.println("Validación de datos personales exitosa.");
            return usuario; // Devuelve el objeto Usuario validado
        }
    }

    public void suscribirse() {
        // TODO: Implement suscribirse
    }
}
