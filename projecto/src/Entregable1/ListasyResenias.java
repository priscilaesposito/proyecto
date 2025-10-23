package Entregable1;

import java.util.LinkedList;

public class ListasyResenias {
    private LinkedList<String> resenias;
    private double puntuacion;
    private int cantPuntuacion;
    private int sumaPuntuacion;

    private boolean moderacion(String resenia) {
        // Lógica para moderar la reseña, por ejemplo, buscar palabras no permitidas.
        return true; // Placeholder para indicar que la reseña es válida.
    }

    public LinkedList<String> getResenias() {
        return resenias;
    }

    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    public void puntuar(int nuevaPuntuacion) {
        this.sumaPuntuacion += nuevaPuntuacion;
        this.cantPuntuacion++;
        this.puntuacion = (double) this.sumaPuntuacion / this.cantPuntuacion;
    }

    public void añadirResenias(String resenia) {
        this.resenias.add(resenia);
    }
}
