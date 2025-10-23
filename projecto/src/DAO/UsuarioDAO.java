
package DAO;

import model.Usuario;
import java.util.List;

public interface UsuarioDAO {

    /**
     * Create: Agrega un nuevo usuario a la base de datos.
     * (Funcionalidad 2) [cite: 106]
     */
    void registrar(Usuario usuario);

    /**
     * Read (One): Lee de la base de datos y devuelve un usuario por su ID.
     * Debe incluir los datos personales asociados (JOIN).
     */
    Usuario buscarPorId(int id);

    /**
     * Read (All): Lee de la base de datos y devuelve todos los usuarios.
     * Debe incluir los datos personales asociados (JOIN).
     * (Funcionalidad 4) [cite: 125, 126]
     */
    List<Usuario> listarTodos();

    /**
     * Update: Modifica un usuario existente en la base de datos.
     */
    void actualizar(Usuario usuario);

    /**
     * Read (Specific): Valida las credenciales de un usuario.
     * (Funcionalidad 6) 
     */
    Usuario validar(String nombreUsuario, String contrasenia);
}