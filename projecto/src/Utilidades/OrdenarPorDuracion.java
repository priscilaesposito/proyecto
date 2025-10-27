package Utilidades;

import java.util.Comparator;
import model.Pelicula;

public class OrdenarPorDuracion implements Comparator<Pelicula> {

    @Override
    public int compare(Pelicula p1, Pelicula p2) {
        // Se usa Double.compare() para comparar valores primitivos de tipo double.
        double duracion1 = p1.getVideo().getDuracion();
        double duracion2 = p2.getVideo().getDuracion();

        // Ordena de menor a mayor duración
        return Double.compare(duracion1, duracion2);
    }
}