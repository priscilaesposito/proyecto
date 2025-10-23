package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.ListasyResenias;

import java.util.ArrayList;

/**
 * DAO para la gestión de la tabla RESENIA.
 */
public class ReseniaDAO {

    /**
     * Funcionalidad 6: Guarda una nueva reseña en la BD. [cite: 139]
     * * @param resenia El objeto Resenia a insertar.
     */
    public void registrarResenia(ListasyResenias resenia) throws SQLException {
        String sql = "INSERT INTO RESENIA (CALIFICACION, COMENTARIO, APROBADO, FECHA_HORA, ID_USUARIO, ID_PELICULA) VALUES (?, ?, ?, ?, ?, ?)";
        // ... (código para PreparedStatement, APROBADO se inserta como 0 o false)
    }

    /**
     * Funcionalidad 7: Muestra las reseñas NO aprobadas. [cite: 141]
     * * @return Una lista de objetos Resenia que no están aprobadas (APROBADO = 0).
     */
    public List<ListasyResenias> listarReseniasNoAprobadas() throws SQLException {
        String sql = "SELECT * FROM RESENIA WHERE APROBADO = 0";
        // ... (código para ejecutar el query y poblar la lista)
        // NOTA: Deberás "completar" los objetos Usuario y Pelicula de cada Resenia
        // llamando a los otros DAOs (ej: UsuarioDAO.getUsuarioPorId)
        return new ArrayList<>(); // Placeholder
    }

    /**
     * Funcionalidad 7: Obtiene una reseña específica por su ID. [cite: 143]
     * * @param idResenia El ID de la reseña a buscar.
     * @return El objeto Resenia, o null si no se encuentra.
     */
    public ListasyResenias getReseniaPorId(int idResenia) throws SQLException {
        String sql = "SELECT * FROM RESENIA WHERE ID = ?";
        // ... (código para buscar la reseña)
        return null; // Placeholder
    }

    /**
     * Funcionalidad 7: Aprueba una reseña cambiando su estado en la BD. [cite: 147]
     * * @param idResenia El ID de la reseña a aprobar.
     */
    public void aprobarResenia(int idResenia) throws SQLException {
        String sql = "UPDATE RESENIA SET APROBADO = 1 WHERE ID = ?";
        // ... (código para PreparedStatement y executeUpdate)
    }
}