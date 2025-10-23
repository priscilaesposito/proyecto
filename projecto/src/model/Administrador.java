package model;

/**
 * Administrador se hereda de {@link Usuario}
 * 
 * @author Juana Sabbione
 * @author Priscila Esposito
 * @version 1.0
 * @since 2025
 * @see Usuario
 */

public class Administrador extends Usuario{
	
	public Administrador() {
    }

    /**
     * Da de baja un titulo del sistema.
     *
     * @param T Titulo a dar de baja.
     */
    public void darDeBaja(Titulo T){
        // Lógica para dar de baja el título
    }

    /**
     * Da de alta un nuevo titulo en el sistema.
     *
     * @param T Titulo a dar de alta.
     */
    public void darAlta(Titulo T){
        // Lógica para dar de alta el título
    }

    /**
     * Modifica la información de un titulo existente.
     *
     * @param T Titulo a modificar.
     */
     public void modificar(Titulo T){
        // Lógica para modificar el título
     }


}
