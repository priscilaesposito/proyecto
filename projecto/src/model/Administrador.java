package model;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import Enumerativo.Genero;

import DAO.PeliculaDAO;
import DAOjdbc.PeliculaDAOjdbc;

/**
 * Administrador se hereda de {@link Usuario}
 * 
 * @author Juana Sabbione
 * @author Priscila Esposito
 * @version 1.0
 * @since 2025
 * @see Usuario
 */

public class Administrador extends Usuario {
    private PeliculaDAO peliD = new DAOjdbc.PeliculaDAOjdbc();

    public Administrador() {
    }

    /**
     * Da de baja un titulo del sistema.
     *
     * @param T Titulo a dar de baja.
     */
    public void darDeBaja(Titulo T) {
        // Lógica para dar de baja el título
    }

    /**
     * Da de alta un nuevo titulo en el sistema.
     *
     * @param T Titulo a dar de alta.
     */
    public void darAlta(Titulo T) {
        // Lógica para dar de alta el título
    }

    /**
     * Modifica la información de un titulo existente.
     *
     * @param T Titulo a modificar.
     */
    public void modificar(Titulo T) {
        // Lógica para modificar el título
    }

    public void validarRegistroPelicula(Pelicula pelicula) {

        List<String> errores = new LinkedList<>();

        if (pelicula.getMetadatos() == null) {
            errores.add("Faltan los metadatos de la pelicula.");
        } else {
            if (pelicula.getMetadatos().getTitulo() == null || pelicula.getMetadatos().getTitulo().trim().isEmpty()) {
                errores.add("El titulo es un campo requerido.");
            }

            if (pelicula.getMetadatos().getDirector() == null
                    || pelicula.getMetadatos().getDirector().trim().isEmpty()) {
                errores.add("El director es un campo requerido.");
            }

        }

        LinkedList<String> generos = pelicula.getGeneros();

        if (generos == null || generos.isEmpty()) {
            errores.add("Se requiere al menos un genero.");
        } else {
            for (String genero : generos) {
                if (!Genero.genValido(genero)) {
                    errores.add("El genero '" + genero + "' no es un genero valido.");
                }
            }
        }

        if (pelicula.getVideo().getDuracion() < 0) {
            errores.add("La duracion/visualizaciones debe ser un numero positivo.");
        }

        if (!errores.isEmpty()) {
            System.out.println("Errores en el registro de pelicula:");
            for (String error : errores) {
                System.out.println("- " + error);
            }
            throw new IllegalArgumentException("El registro de la pelicula fallo por errores de validacion.");
        }
    }

    public void almacenarPelicula(Pelicula pelicula) throws SQLException {
        peliD.registrar(pelicula);

    }
}
