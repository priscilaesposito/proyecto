
package DAO;

import model.Usuario;
import java.util.List;
import java.sql.SQLException;

public interface UsuarioDAO {

    /**
     * Create: Agrega un nuevo usuario a la base de datos.
     * (Funcionalidad 2) [cite: 106]
     */
    void registrar(Usuario usuario) throws SQLException;

    /**
     * Read (One): Lee de la base de datos y devuelve un usuario por su ID.
     * Debe incluir los datos personales asociados (JOIN).
     */
    Usuario buscarPorId(int id) throws SQLException;

    /**
     * Read (All): Lee de la base de datos y devuelve todos los usuarios.
     * Debe incluir los datos personales asociados (JOIN).
     * (Funcionalidad 4) [cite: 125, 126]
     */
    List<Usuario> listarTodos() throws SQLException;

    /**
     * Update: Modifica un usuario existente en la base de datos.
     */
    void actualizar(Usuario usuario) throws SQLException;

    /**
     * Read (Specific): Valida las credenciales de un usuario.
     * (Funcionalidad 6) 
     */
    Usuario validar(String nombreUsuario, String contrasenia) throws SQLException;
     
    Usuario buscar(String nombreUsuario) throws SQLException;

}