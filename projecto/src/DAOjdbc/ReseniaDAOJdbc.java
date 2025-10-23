// Archivo: src/daojdbc/ReseniaDAOJdbc.java
package DAOjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import DAO.ReseniaDAO;
import DB.BaseDeDatos;
import model.ListasyResenias;

public class ReseniaDAOJdbc implements ReseniaDAO {

    @Override
    public void registrar(ListasyResenias resenia) {
        // [cite: 92]
        // Se asume APROBADO por defecto 0 (no aprobado)
        String sql = "INSERT INTO RESENIA (CALIFICACION, COMENTARIO, APROBADO, FECHA_HORA, ID_USUARIO, ID_PELICULA) " +
                       "VALUES (?, ?, 0, ?, ?, ?)";
        
        try (Connection conn = BaseDeDatos.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, resenia.getCalificacion());
            pstmt.setString(2, resenia.getComentario());
            pstmt.setTimestamp(3, Timestamp.valueOf(resenia.getFechaHora())); 
            pstmt.setInt(4, resenia.getIdUsuario());
            pstmt.setInt(5, resenia.getIdPelicula());
            
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error al registrar reseña: " + e.getMessage());
        }
    }

    @Override
    public ListasyResenias buscarPorId(int id) {
        String sql = "SELECT * FROM RESENIA WHERE ID = ?";
        try (Connection conn = BaseDeDatos.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToResenia(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar reseña: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<ListasyResenias> listarTodos() {
        List<ListasyResenias> lista = new ArrayList<>();
        String sql = "SELECT * FROM RESENIA";
        try (Connection conn = BaseDeDatos.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                lista.add(mapResultSetToResenia(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar todas las reseñas: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void actualizar(ListasyResenias resenia) {
        String sql = "UPDATE RESENIA SET CALIFICACION = ?, COMENTARIO = ?, APROBADO = ?, " +
                       "FECHA_HORA = ?, ID_USUARIO = ?, ID_PELICULA = ? WHERE ID = ?";
        try (Connection conn = BaseDeDatos.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, resenia.getCalificacion());
            pstmt.setString(2, resenia.getComentario());
            pstmt.setInt(3, resenia.isAprobado() ? 1 : 0);
            pstmt.setTimestamp(4, Timestamp.valueOf(resenia.getFechaHora()));
            pstmt.setInt(5, resenia.getIdUsuario());
            pstmt.setInt(6, resenia.getIdPelicula());
            pstmt.setInt(7, resenia.getId()); // ID para el WHERE
            
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error al actualizar la reseña: " + e.getMessage());
        }
    }

    @Override
    public List<ListasyResenias> listarNoAprobadas() {
        // 
        List<ListasyResenias> lista = new ArrayList<>();
        String sql = "SELECT * FROM RESENIA WHERE APROBADO = 0";

        try (Connection conn = BaseDeDatos.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                lista.add(mapResultSetToResenia(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar reseñas no aprobadas: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void aprobarResenia(int idResenia) {
        // 
        String sql = "UPDATE RESENIA SET APROBADO = 1 WHERE ID = ?";
        try (Connection conn = BaseDeDatos.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idResenia);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error al aprobar la reseña: " + e.getMessage());
        }
    }

    /**
     * Helper para convertir un ResultSet en un objeto Resenia.
     */
    private ListasyResenias mapResultSetToResenia(ResultSet rs) throws SQLException {
        ListasyResenias resenia = new ListasyResenias();
        resenia.setId(rs.getInt("ID"));
        resenia.setCalificacion(rs.getInt("CALIFICACION"));
        resenia.setComentario(rs.getString("COMENTARIO"));
        resenia.setAprobado(rs.getInt("APROBADO") == 1);
        resenia.setFechaHora(rs.getTimestamp("FECHA_HORA").toLocalDateTime());
        resenia.setIdUsuario(rs.getInt("ID_USUARIO"));
        resenia.setIdPelicula(rs.getInt("ID_PELICULA"));
        return resenia;
    }
}