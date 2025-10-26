
package DAO;

import model.Usuario;

import java.sql.SQLException;
import java.util.List;

public interface DatosPersonalesDAO {

    /**
     * Create: Agrega nuevos datos personales a la base de datos.
     * (Funcionalidad 1) [cite: 95]
     */
    void registrar(Usuario datos);

    /**
     * Read (One): Lee de la base de datos y devuelve datos personales por su ID.
     */
    Usuario buscarPorId(int id);

    /**
     * Read (All): Lee de la base de datos y devuelve todas las personas.
     * (Necesario para Funcionalidad 2) [cite: 107]
     */
    List<Usuario> listarTodos();

    /**
     * Update: Modifica datos personales existentes en la base de datos.
     */
    void actualizar(Usuario datos);

    /**
     * Read (Specific): Busca datos personales por DNI para validación.
     * (Validación 1) 
     */
    Usuario buscarPorDni(int dni);


    /**
     * Verifica si existe un DNI en la base de datos.
     * @param dni El DNI a verificar
     * @return true si el DNI ya existe, false si no existe
     * @throws SQLException 
     */
    boolean existeDNI(int dni) throws SQLException;
}