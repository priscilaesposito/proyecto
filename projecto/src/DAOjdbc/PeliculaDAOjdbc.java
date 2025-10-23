
package DAOjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

import DAO.PeliculaDAO;
import DB.BaseDeDatos;
import model.Pelicula;

public class PeliculaDAOjdbc implements PeliculaDAO {

    @Override
    public void registrar(Pelicula pelicula) {
        // [cite: 70-77]
        String sql = "INSERT INTO PELICULA (GENERO, TITULO, RESUMEN, DIRECTOR, DURACION) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = BaseDeDatos.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            //pri
            LinkedList<String> generos = pelicula.getGeneros();
            int k;
            String genero = "";
            for (k = 0; k < generos.size() - 1; k++) {
                genero += generos.get(k) + ", ";
                
            }
            pstmt.setString(1, genero); 
            pstmt.setString(2, pelicula.getMetadatos().getTitulo());
            pstmt.setString(3, pelicula.getMetadatos().getSipnosis());
            pstmt.setString(4, pelicula.getMetadatos().getDirector());
            pstmt.setDouble(5, pelicula.getVideo().getDuracion());
            
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al registrar la película: " + e.getMessage());
        }
    }

    @Override
    public Pelicula buscarPorId(int id) {
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
    public List<Pelicula> listarTodos() {
        List<Pelicula> peliculas = new ArrayList<>();
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
    public void actualizar(Pelicula pelicula) {
        String sql = "UPDATE PELICULA SET GENERO = ?, TITULO = ?, RESUMEN = ?, DIRECTOR = ?, DURACION = ? WHERE ID = ?";
        try (Connection conn = BaseDeDatos.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
               //pri
            LinkedList<String> generos = pelicula.getGeneros();
            int k;
            String genero = "";
            for (k = 0; k < generos.size() - 1; k++) {
                genero += generos.get(k) + ", ";
                
            }
            pstmt.setString(1, genero); 
            pstmt.setString(2, pelicula.getMetadatos().getTitulo());
            pstmt.setString(3, pelicula.getMetadatos().getSipnosis());
            pstmt.setString(4, pelicula.getMetadatos().getDirector());
            pstmt.setDouble(5, pelicula.getVideo().getDuracion());
            pstmt.setInt(6, pelicula.getID()); // ID para el WHERE
            
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al actualizar la película: " + e.getMessage());
        }
    }

    /**
     * Helper para convertir un ResultSet en un objeto Pelicula.
     */
    private Pelicula mapResultSetToPelicula(ResultSet rs) throws SQLException {
        Pelicula p = new Pelicula();
        p.setID(rs.getInt("ID"));
        p.getMetadatos().setTitulo(rs.getString("TITULO"));
        
        // Procesar géneros: dividir el string por comas y agregar cada uno a la lista
        String generos = rs.getString("GENERO");
        if (generos != null) {
            for (String genero : generos.split(",")) {
            p.anadirGeneros(genero.trim());
            }
        }
        
        p.getMetadatos().setSipnosis(rs.getString("RESUMEN"));
        p.getMetadatos().setDirector(rs.getString("DIRECTOR"));
        // Nota: La duración se maneja en la clase Video, no directamente en Pelicula

        return p;
    }
}