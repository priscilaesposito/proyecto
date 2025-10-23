
package DAO;

import java.util.List;
import model.Pelicula;

public interface PeliculaDAO {

    /**
     * Create: Agrega una nueva pelicula a la base de datos.
     * (Funcionalidad 3) [cite: 118]
     */
    void registrar(Pelicula pelicula);

    /**
     * Read (One): Lee de la base de datos y devuelve una pelicula por su ID.
     */
    Pelicula buscarPorId(int id);

    /**
     * Read (All): Lee de la base de datos y devuelve todas las peliculas.
     * (Funcionalidad 5) [cite: 129]
     */
    List<Pelicula> listarTodos();

    /**
     * Update: Modifica una pelicula existente en la base de datos.
     */
    void actualizar(Pelicula pelicula);
}
