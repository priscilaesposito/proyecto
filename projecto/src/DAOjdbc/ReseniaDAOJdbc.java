
package DAOjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.ReseniaDAO;
import DB.BaseDeDatos;
import model.Resenia;

public class ReseniaDAOjdbc implements ReseniaDAO {

    @Override
    public void registrar(Resenia resenia) {

        String sql = "INSERT INTO RESENIA (CALIFICACION, COMENTARIO, APROBADO, FECHA_HORA, ID_USUARIO, ID_PELICULA) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = BaseDeDatos.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, resenia.getCalificacion());
            pstmt.setString(2, resenia.getComentario());
            pstmt.setInt(3, resenia.isAprobado());
            pstmt.setString(4, resenia.getFechaHora());
            pstmt.setInt(5, resenia.getID_Usuario());
            pstmt.setInt(6, resenia.getID_Pelicula());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al registrar reseña: " + e.getMessage());
        }
    }

    @Override
    public Resenia buscarPorId(int id) {
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
    public List<Resenia> listarNoAprobadas() {
        List<Resenia> lista = new ArrayList<>();
        String sql = "SELECT * FROM RESENIA WHERE APROBADO = 0";

        try (Connection conn = BaseDeDatos.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapResultSetToResenia(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar resenias no aprobadas: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void aprobarResenia(int idResenia) {
        String sql = "UPDATE RESENIA SET APROBADO = 1 WHERE ID = ?";
        try (Connection conn = BaseDeDatos.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idResenia);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al aprobar la resenia: " + e.getMessage());
        }
    }

    private Resenia mapResultSetToResenia(ResultSet rs) throws SQLException {
        Resenia resenia = new Resenia();
        resenia.setID_Resenia(rs.getInt("ID"));
        resenia.setCalificacion(rs.getInt("CALIFICACION"));
        resenia.setComentario(rs.getString("COMENTARIO"));
        resenia.setAprobado(rs.getInt("APROBADO"));
        resenia.setFechaHora(rs.getString("FECHA_HORA"));
        resenia.setID_Usuario(rs.getInt("ID_USUARIO"));
        resenia.setID_Pelicula(rs.getInt("ID_PELICULA"));
        return resenia;
    }

}