// Archivo: src/daojdbc/PeliculaDAOJdbc.java
package DAOjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.PeliculaDAO;
import DB.BaseDeDatos;
import model.Titulo;

public class PeliculaDAOJdbc implements PeliculaDAO {

    @Override
    public void registrar(Titulo pelicula) {
        // [cite: 70-77]
        String sql = "INSERT INTO PELICula (GENERO, TITULO, RESUMEN, DIRECTOR, DURACION) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = BaseDeDatos.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, pelicula.getGenero()); 
            pstmt.setString(2, pelicula.getTitulo());
            pstmt.setString(3, pelicula.getResumen());
            pstmt.setString(4, pelicula.getDirector());
            pstmt.setDouble(5, pelicula.getDuracion());
            
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al registrar la película: " + e.getMessage());
        }
    }

    @Override
    public Titulo buscarPorId(int id) {
        String sql = "SELECT * FROM PELICULA WHERE ID = ?";
        try (Connection conn = BaseDeDatos.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToPelicula(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar película: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Titulo> listarTodos() {
        List<Titulo> peliculas = new ArrayList<>();
        String sql = "SELECT * FROM PELICULA";
        
        try (Connection conn = BaseDeDatos.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                peliculas.add(mapResultSetToPelicula(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar películas: " + e.getMessage());
        }
        return peliculas;
    }

    @Override
    public void actualizar(Titulo pelicula) {
        String sql = "UPDATE PELICULA SET GENERO = ?, TITULO = ?, RESUMEN = ?, DIRECTOR = ?, DURACION = ? WHERE ID = ?";
        try (Connection conn = BaseDeDatos.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, pelicula.getGenero()); 
            pstmt.setString(2, pelicula.getTitulo());
            pstmt.setString(3, pelicula.getResumen());
            pstmt.setString(4, pelicula.getDirector());
            pstmt.setDouble(5, pelicula.getDuracion());
            pstmt.setInt(6, pelicula.getId()); // ID para el WHERE
            
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al actualizar la película: " + e.getMessage());
        }
    }

    /**
     * Helper para convertir un ResultSet en un objeto Pelicula.
     */
    private Titulo mapResultSetToPelicula(ResultSet rs) throws SQLException {
        Titulo p = new Titulo();
        p.setId(rs.getInt("ID"));
        p.setTitulo(rs.getString("TITULO"));
        p.setGenero(rs.getString("GENERO"));
        p.setResumen(rs.getString("RESUMEN"));
        p.setDirector(rs.getString("DIRECTOR"));
        p.setDuracion(rs.getDouble("DURACION"));
        return p;
    }
}