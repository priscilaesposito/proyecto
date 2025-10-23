package app;

import model.Usuario;

import java.util.Scanner;

import DAO.UsuarioDAO;
import DAO.conexion;
import DAOjdbc.UsuarioDAOjdbc;

import java.sql.SQLException;

/**
 * Clase principal que inicializa la aplicación y coordina el flujo de registro.
 * Esta clase contiene el método main y el control de la aplicación, llamando a:
 * 1. Usuario.solicitarDatos() (para la interacción y validación de formato).
 * 2. UsuarioDAO (para la validación de unicidad y persistencia).
 */
public class Main {

     private static UsuarioDAO usuarioDAO = new UsuarioDAOjdbc();
    private static Scanner scanner = new Scanner(System.in);
    
    /**
     * Implementa la funcionalidad 1: Registrar Datos Personales.
     * Coordina la entrada de datos, la validación de unicidad y la persistencia.
     */
    public static void registrarUsuario() {
        
        // 1. SOLICITAR DATOS Y VALIDAR FORMATO (Llamada al método estático de Usuario)
        Usuario nuevoUsuario = Usuario.solicitarDatos(scanner);
        
        if (nuevoUsuario == null) {
            System.out.println("\nRegistro de usuario cancelado.");
            return;
        }

        try {
            // 2. VALIDACIÓN DE UNICIDAD DE DNI (Lógica de Persistencia en DAO)
            if (usuarioDAO.existeDNI(nuevoUsuario.getDNI())) {
                System.out.println("\n[ERROR FATAL] El DNI " + nuevoUsuario.getDNI() + " ya está registrado. No se puede guardar.");
                return;
            }

            // 3. MOSTRAR Y CONFIRMAR al usuario
            mostrarDatosIngresados(nuevoUsuario);
            
            System.out.print("\n¿Son estos datos correctos? (S/N): ");
            String confirmacion = scanner.nextLine().trim().toUpperCase();

            if ("S".equals(confirmacion)) {
                // 4. GUARDAR EN LA BASE DE DATOS
                usuarioDAO.registrar(nuevoUsuario);
                System.out.println("\n¡REGISTRO EXITOSO! Los datos se han guardado correctamente.");
            } else {
                System.out.println("\nRegistro cancelado por el usuario. No se guardó en la Base de Datos.");
            }

        } 
        catch (SQLException e) {
            System.err.println("\n[ERROR DE BD] Falló la operación de la base de datos: " + e.getMessage());
        }
    }
    
    /**
     * Método auxiliar para mostrar los datos ingresados.
     */
    private static void mostrarDatosIngresados(Usuario u) {
        System.out.println("\n--- DATOS INGRESADOS ---");
        System.out.println("DNI: " + u.getDNI());
        System.out.println("Nombre: " + u.getNombre());
        System.out.println("Apellido: " + u.getApellido());
        System.out.println("Correo: " + u.getCorreo());
        System.out.println("Contraseña: [OCULTA]");
        System.out.println("Idioma: " + u.getIdioma());
        System.out.println("Fecha Nacimiento: " + u.getFechaNacimiento());
    }
    
    public static void main(String[] args) {
        try {
            // Inicializar la conexión (opcional, pero buena práctica)
            conexion.conectar(); 
            registrarUsuario();
        } 
        catch (SQLException e) {
            System.out.println("Error al iniciar la aplicación: La conexión a la BD falló.");
        } 

        finally {
            conexion.desconectar(); // Cierra la conexión al finalizar
            scanner.close();
        }
    }
}
