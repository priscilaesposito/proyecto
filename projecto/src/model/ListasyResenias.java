package model;

import java.util.LinkedList;
import java.util.List;

import DAO.ReseniaDAO;
import DAOjdbc.ReseniaDAOjdbc;

public class ListasyResenias {
    private ReseniaDAO RD = new DAOjdbc.ReseniaDAOjdbc();
    private LinkedList<Resenia> resenias;
    private double puntuacion;
    private int cantPuntuacion;
    private int sumaPuntuacion;

    private boolean moderacion(Resenia resenia) {
        return true; // puesto de esta forma para no tener errores de compilacion.
    }

    public LinkedList<Resenia> getResenias() {
        return resenias;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    public void puntuar(int nuevaPuntuacion) {
        this.sumaPuntuacion += nuevaPuntuacion;
        this.cantPuntuacion++;
        this.puntuacion = (double) this.sumaPuntuacion / this.cantPuntuacion;
    }

    public void aniadirResenias(Resenia resenia) {
        RD.registrar(resenia);
    }

    public List<Resenia> listarReseniasNoAprobadas() {
        return RD.listarNoAprobadas();
    }

    public Resenia validarResenia(int IDresenia) { // verifica si la reseña existe, en ese caso devuelve la resenia,
                                                   // caso contrario informa y devuelve null.
        Resenia reseñaEncontrada = RD.buscarPorId(IDresenia);
        if (reseñaEncontrada == null) {
            System.out.println(" Error: La reseña con ID " + IDresenia + " no existe.");
            return null;
        }
        return reseñaEncontrada;
    }

    public void aprobarResenia(int idResenia) {
        RD.aprobarResenia(idResenia);
    }

}
