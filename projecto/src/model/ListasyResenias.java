package model;

import java.util.LinkedList;
import model.Resenia;

public class ListasyResenias {
    private LinkedList<Resenia> resenias;
    private double puntuacion;
    private int cantPuntuacion;
    private int sumaPuntuacion;

    private boolean moderacion(Resenia resenia) {
        return true;
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
        this.resenias.add(resenia);
    }
}
