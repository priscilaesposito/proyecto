
package DAO;

import java.util.List;
import model.Resenia;

public interface ReseniaDAO {

    void registrar(Resenia resenia);

    Resenia buscarPorId(int id);

    List<Resenia> listarNoAprobadas();

    void aprobarResenia(int idResenia);
}