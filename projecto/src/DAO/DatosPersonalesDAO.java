package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Usuario;

import java.util.ArrayList;

/**
 * DAO para la gestión de la tabla DATOS_PERSONALES.
 */
public class DatosPersonalesDAO {

    /**
     * Funcionalidad 1: Registra una nueva persona en la BD. [cite: 98]
     * * @param datos El objeto Usuario a insertar.
     * @return el ID generado para la persona registrada.
     */
    public int registrarDatosPersonales(Usuario datos) throws SQLException {
        String sql = "INSERT INTO DATOS_PERSONALES (NOMBRES, APELLIDO, DNI) VALUES (?, ?, ?)";
        // ... (código para PreparedStatement y executeUpdate)
        // ... (debe retornar el ID generado)
        return 0; // Placeholder
    }

    /**
     * Funcionalidad 1 (Validación): Verifica si un DNI ya existe. [cite: 101]
     * * @param dni El DNI a verificar.
     * @return true si el DNI ya existe, false en caso contrario.
     */
    public boolean verificarDniUnico(int dni) throws SQLException {
        String sql = "SELECT COUNT(*) FROM DATOS_PERSONALES WHERE DNI = ?";
        // ... (código para PreparedStatement y executeQuery)
        return false; // Placeholder
    }

    /**
     * Funcionalidad 2: Lista las personas que aún no tienen un usuario asociado.
     * [cite: 107]
     * * @return Una lista de objetos Usuario.
     */
    public List<Usuario> listarPersonasSinUsuario() throws SQLException {
        String sql = "SELECT * FROM DATOS_PERSONALES WHERE ID NOT IN (SELECT ID_DATOS_PERSONALES FROM USUARIO)";
        // ... (código para ejecutar el query y poblar una lista)
        return new ArrayList<>(); // Placeholder
    }

    /**
     * Método de ayuda para la Funcionalidad 4: Obtiene los datos de una persona
     * por su ID.
     * * @param id El ID de la persona.
     * @return Un objeto Usuario.
     */
    public Usuario getDatosPersonalesPorId(int id) throws SQLException {
        String sql = "SELECT * FROM DATOS_PERSONALES WHERE ID = ?";
        // ... (código para buscar y devolver el objeto)
        return null; // Placeholder
    }
}