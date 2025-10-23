package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Usuario;

import java.util.ArrayList;

/**
 * DAO para la gestión de la tabla USUARIO.
 */
public class UsuarioDAO {

    /**
     * Funcionalidad 2: Registra un nuevo usuario en la BD. [cite: 113]
     * * @param usuario El objeto Usuario a insertar (debe tener el ID de
     * DatosPersonales).
     */
    public void registrarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO USUARIO (NOMBRE_USUARIO, EMAIL, CONTRASENIA, ID_DATOS_PERSONALES) VALUES (?, ?, ?, ?)";
        // ... (código para PreparedStatement)
    }

    /**
     * Funcionalidad 4: Lista todos los usuarios. [cite: 126]
     * El ordenamiento (por Nombre de Usuario o Email) [cite: 127] se debe hacer en
     * Java
     * usando las interfaces (Comparable/Comparator) [cite: 128] sobre la lista
     * devuelta.
     * * @return Una lista de todos los objetos Usuario.
     */
    public List<Usuario> listarUsuarios() throws SQLException {
        String sql = "SELECT * FROM USUARIO";
        List<Usuario> usuarios = new ArrayList<>();
        // ... (código para ejecutar el query)
        // NOTA: Aquí solo poblarás los datos de USUARIO.
        // En tu capa de servicio (App.java) deberás llamar a
        // DatosPersonalesDAO.getDatosPersonalesPorId()
        // para "completar" cada objeto Usuario.
        return usuarios;
    }

    /**
     * Funcionalidad 6: Valida las credenciales de un usuario. [cite: 135]
     * * @param nombreUsuario El nombre de usuario.
     * @param contrasenia   La contraseña.
     * @return El objeto Usuario si la validación es exitosa, null en caso
     * contrario.
     */
    public Usuario validarLogin(String nombreUsuario, String contrasenia) throws SQLException {
        String sql = "SELECT * FROM USUARIO WHERE NOMBRE_USUARIO = ? AND CONTRASENIA = ?";
        // ... (código para buscar el usuario)
        return null; // Placeholder
    }
}