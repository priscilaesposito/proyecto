package DB; 


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Titulo;

import java.util.ArrayList;

/**
 * DAO para la gestión de la tabla PELICULA.
 */
public class PeliculaDAO {

    /**
     * Funcionalidad 3: Registra una nueva película en la BD. [cite: 120]
     * * @param pelicula El objeto Pelicula a insertar.
     */
    public void registrarPelicula(Titulo pelicula) throws SQLException {
        String sql = "INSERT INTO PELICULA (GENERO, TITULO, RESUMEN, DIRECTOR, DURACION) VALUES (?, ?, ?, ?, ?)";
        // ... (código para PreparedStatement)
    }

    /**
     * Funcionalidad 5: Lista todas las películas. [cite: 130]
     * El ordenamiento (Título, Género, Duración) [cite: 131] se debe hacer en
     * Java
     * usando las interfaces (Comparable/Comparator) [cite: 132] sobre la lista
     * devuelta.
     * * @return Una lista de todos los objetos Pelicula.
     */
    public List<Titulo> listarPeliculas() throws SQLException {
        String sql = "SELECT * FROM PELICULA";
        // ... (código para ejecutar el query y poblar la lista)
        return new ArrayList<>(); // Placeholder
    }

    /**
     * Funcionalidad 6: Obtiene un listado de películas para mostrar al crear
     * reseña. [cite: 136]
     * (Es similar a listarPeliculas, pero lo separamos por claridad)
     * * @return Una lista de todos los objetos Pelicula.
     */
    public List<Titulo> getPeliculasDisponibles() throws SQLException {
        return listarPeliculas(); // Reutilizamos el método anterior
    }
}
