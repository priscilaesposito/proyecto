
package DAO;

import model.ListasyResenias;
import java.util.List;

public interface ReseniaDAO {

    /**
     * Create: Agrega una nueva reseña a la base de datos.
     * (Funcionalidad 6) [cite: 133]
     */
    void registrar(ListasyResenias resenia);

    /**
     * Read (One): Lee de la base de datos y devuelve una reseña por su ID.
     * (Necesario para Funcionalidad 7) [cite: 143]
     */
    ListasyResenias buscarPorId(int id);

    /**
     * Read (All): Lee de la base de datos y devuelve todas las reseñas.
     */
    List<ListasyResenias> listarTodos();

    /**
     * Update: Modifica una reseña existente en la base de datos.
     */
    void actualizar(ListasyResenias resenia);

    /**
     * Read (Specific): Devuelve un listado de reseñas NO aprobadas.
     * (Funcionalidad 7) 
     */
    List<ListasyResenias> listarNoAprobadas();

    /**
     * Update (Specific): Aprueba una reseña específica por su ID.
     * (Funcionalidad 7) 
     */
    void aprobarResenia(int idResenia);
}