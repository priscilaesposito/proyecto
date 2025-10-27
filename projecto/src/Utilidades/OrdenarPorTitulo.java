package Utilidades;

import java.util.Comparator;
import model.Pelicula;

public class OrdenarPorTitulo implements Comparator<Pelicula> {

    @Override
    public int compare(Pelicula p1, Pelicula p2) {
        // Se ordena alfabéticamente por el Título, obtenido del objeto metadatos.
        // Asume que getMetadatos() y getTitulo() no son null (esto debe ser validado
        // antes).
        return p1.getMetadatos().getTitulo().compareTo(p2.getMetadatos().getTitulo());
    }
}