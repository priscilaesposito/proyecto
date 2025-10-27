
package DAO;

import model.Usuario;

import java.sql.SQLException;
import java.util.List;

public interface DatosPersonalesDAO {

    void registrar(Usuario datos) throws SQLException;

    Usuario buscarPorId(int id);

    List<Usuario> listarTodos() throws SQLException;

    void actualizar(Usuario datos);

    Usuario buscarPorDni(int dni);

    boolean existeDNI(int dni) throws SQLException;
}