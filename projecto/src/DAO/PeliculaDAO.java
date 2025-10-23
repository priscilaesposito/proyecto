
package DAO;

import java.util.List;
import model.Titulo;

public interface PeliculaDAO {

    /**
     * Create: Agrega una nueva pelicula a la base de datos.
     * (Funcionalidad 3) [cite: 118]
     */
    void registrar(Titulo pelicula);

    /**
     * Read (One): Lee de la base de datos y devuelve una pelicula por su ID.
     */
    Titulo buscarPorId(int id);

    /**
     * Read (All): Lee de la base de datos y devuelve todas las peliculas.
     * (Funcionalidad 5) [cite: 129]
     */
    List<Titulo> listarTodos();

    /**
     * Update: Modifica una pelicula existente en la base de datos.
     */
    void actualizar(Titulo pelicula);
}
