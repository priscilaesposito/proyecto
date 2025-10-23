// Archivo: src/daojdbc/PeliculaDAOJdbc.java
package DAOjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.PeliculaDAO;
import DB.BaseDeDatos; // Asumiendo que tu clase de conexión está en 'db'
import model.Titulo;

// Esta es una CLASE que IMPLEMENTA la interfaz
public class PeliculaDAOjdbc implements PeliculaDAO {

    @Override
    public void registrarPelicula(Titulo pelicula) {
        String sql = "INSERT INTO PELICULA (GENERO, TITULO, RESUMEN, DIRECTOR, DURACION) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = BaseDeDatos.conectar(); // Obtienes la conexión
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, pelicula.getGenero()); // Asumiendo que tienes estos métodos
            pstmt.setString(2, pelicula.getTitulo());
            pstmt.setString(3, pelicula.getResumen());
            pstmt.setString(4, pelicula.getDirector());
            pstmt.setDouble(5, pelicula.getDuracion());
            
            pstmt.executeUpdate();
            System.out.println("Película registrada en la BD.");

        } catch (SQLException e) {
            System.out.println("Error al registrar la película: " + e.getMessage());
        }
    }

    @Override
    public List<Pelicula> listarPeliculas() {
        List<Pelicula> peliculas = new ArrayList<>();
        String sql = "SELECT * FROM PELICULA";
        
        try (Connection conn = BaseDeDatos.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                // Creas un objeto Pelicula con los datos del ResultSet
                Pelicula p = new Pelicula(); 
                p.setId(rs.getInt("ID"));
                p.setTitulo(rs.getString("TITULO"));
                p.setGenero(rs.getString("GENERO"));
                p.setResumen(rs.getString("RESUMEN"));
                p.setDirector(rs.getString("DIRECTOR"));
                p.setDuracion(rs.getDouble("DURACION"));
                
                peliculas.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar películas: " + e.getMessage());
        }
        return peliculas;
    }
}