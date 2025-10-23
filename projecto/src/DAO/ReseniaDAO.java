
package DAO;

import model.Resenia;
import java.util.List;

public interface ReseniaDAO {

    /**
     * Create: Agrega una nueva reseña a la base de datos.
     * (Funcionalidad 6) [cite: 133]
     */
    void registrar(Resenia resenia);

    /**
     * Read (One): Lee de la base de datos y devuelve una reseña por su ID.
     * (Necesario para Funcionalidad 7) [cite: 143]
     */
    Resenia buscarPorId(int id);

    /**
     * Read (All): Lee de la base de datos y devuelve todas las reseñas.
     */
    List<Resenia> listarTodos();

    /**
     * Update: Modifica una reseña existente en la base de datos.
     */
    void actualizar(Resenia resenia);

    /**
     * Read (Specific): Devuelve un listado de reseñas NO aprobadas.
     * (Funcionalidad 7) 
     */
    List<Resenia> listarNoAprobadas();

    /**
     * Update (Specific): Aprueba una reseña específica por su ID.
     * (Funcionalidad 7) 
     */
    void aprobarResenia(int idResenia);
}