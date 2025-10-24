package model;

import model.Usuario;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private boolean dniUnico(int dni) {
        return UDJ.existeDNI(dni);
    }

    // --- LÓGICA PRINCIPAL DE VALIDACIÓN ---

    /**
     * Valida todos los campos de un objeto Usuario según los requisitos.
     * 
     * @param usuario El objeto Usuario con los datos a validar.
     * @return El mismo objeto Usuario si es válido, o null si hay errores.
     */
    public Usuario validacionDatos(Usuario usuario) {

        // Objeto para recolectar todos los errores encontrados
        StringBuilder errores = new StringBuilder();

        // 1. VERIFICAR NULIDAD DEL OBJETO
        if (usuario == null) {
            errores.append("Error fatal: El objeto Usuario es nulo.\n");
            System.out.println(errores.toString());
            return null;
        }

        // --- VALIDACIONES DE REQUISITOS CLAVE ---

        // 2. VALIDACIÓN DE NOMBRE
        if (usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()) {
            errores.append("- **Nombre**: No puede estar vacío.\n");
        } else {
            // Regex: \p{L} = Cualquier carácter de letra de cualquier idioma (incluye
            // tildes, Ñ, etc.).
            // \s = espacios. El '+' asegura que haya uno o más caracteres válidos.
            if (!usuario.getNombre().trim().matches("[\\p{L}\\s]+")) {
                errores.append("- **Nombre**: Solo puede contener letras y espacios (no números o símbolos).\n");
            }
        }

        // 3. VALIDACIÓN DE APELLIDO
        if (usuario.getApellido() == null || usuario.getApellido().trim().isEmpty()) {
            errores.append("- **Apellido**: No puede estar vacío.\n");
        } else {
            if (!usuario.getApellido().trim().matches("[\\p{L}\\s]+")) {
                errores.append("- **Apellido**: Solo puede contener letras y espacios (no números o símbolos).\n");
            }
        }

        // 4. VALIDACIÓN DE DNI
        if (usuario.getDNI() <= 0) {
            errores.append("- **DNI**: Debe ingresar un número de DNI válido.\n");
        } else {
            // Se puede añadir aquí una validación de longitud (ej. 7 u 8 dígitos)
            // if (String.valueOf(dni).length() < 7 || String.valueOf(dni).length() > 8) {
            // errores.append("- **DNI**: Debe tener entre 7 y 8 dígitos.\n");
            // }

            // 5. VALIDACIÓN DE DNI ÚNICO (Solo si el DNI es un valor positivo)
            if (!dniUnico(usuario.getDNI())) {
                errores.append("- **DNI**: El número ya se encuentra registrado en el sistema.\n");
            }
        }

        // --- VALIDACIONES DE OTROS CAMPOS REQUERIDOS ---

        // 6. VALIDACIÓN DE USERNAME (Ejemplo: no vacío)
        if (usuario.getUsername() == null || usuario.getUsername().trim().isEmpty()) {
            errores.append("- **Username**: No puede estar vacío.\n");
        }

        // 7. VALIDACIÓN DE CORREO (Ejemplo: no vacío y formato básico)
        if (usuario.getCorreo() == null || usuario.getCorreo().trim().isEmpty()) {
            errores.append("- **Correo**: No puede estar vacío.\n");
        } else {
            // Regex simple para formato de email (se puede usar una más compleja)
            String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
            Pattern pattern = Pattern.compile(EMAIL_REGEX);
            Matcher matcher = pattern.matcher(usuario.getCorreo());
            if (!matcher.matches()) {
                errores.append("- **Correo**: El formato del correo electrónico es incorrecto.\n");
            }
        }

        // 8. VALIDACIÓN DE CONTRASEÑA (Ejemplo: longitud mínima)
        if (usuario.getContrasenia() == null || usuario.getContrasenia().length() < 8) {
            errores.append("- **Contraseña**: Debe tener al menos 8 caracteres.\n");
        }

        // --- RESULTADO FINAL DE LA VALIDACIÓN ---

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
