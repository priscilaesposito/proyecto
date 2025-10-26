package model;

import model.Usuario;

import java.util.Arrays;
import java.util.LinkedList;

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

    private boolean dniUnico(int dni) {
        return UDJ.existeDNI(dni);
    }

    private static void Valido(String linea) {
        LinkedList<Character> caracteresInvalidos = new LinkedList<>(Arrays.asList('0', '1', '2', '3', '4', '5', '6',
                '7', '8', '9', '!', '"', '#', '$', '%', '&', '/', '(', ')', '=', '?', '¡', '¿', '+', '-', '*', '{', '}',
                '[', ']', '^', '`', '´', '¨', ';', ':', '.', ','));
        for (char c : linea.toCharArray()) {
            if (caracteresInvalidos.contains(c)) {
                System.out.println("El nombre no debe contener números ni caracteres especiales. Ingrese nuevamente:");
                String nuevaLinea = scanner.nextLine();
                Valido(nuevaLinea);
                return;
            }
        }
        return;
    }

    /**
     * Valida todos los campos de un objeto Usuario según los requisitos.
     * 
     * @param usuario El objeto Usuario con los datos a validar de datos personales.
     * @return El mismo objeto Usuario si es válido, o null si hay errores.
     */
    private void validacionDatosPersonales(Usuario usuario) {
        // Validar unicidad de DNI
        if (!dniUnico(usuario.getDNI())) {
            throw new IllegalArgumentException("El DNI ya existe en la base de datos.");
        }

    }

    public void suscribirse() {
        // TODO: Implement suscribirse
    }
}
