package app;

import model.Fecha;
import model.Usuario;

import java.util.LinkedList;
import java.util.Scanner;

import DAO.UsuarioDAO;
import DAO.conexion;
import DAOjdbc.DatosPersonalesDAOJdbc;
import model.GestionUsuario;
import DAOjdbc.UsuarioDAOjdbc;

import java.sql.SQLException;
import java.util.Arrays;

/**
 * Clase principal que inicializa la aplicación y coordina el flujo de registro.
 * Esta clase contiene el método main y el control de la aplicación, llamando a:
 * 1. Usuario.solicitarDatos() (para la interacción y validación de formato).
 * 2. UsuarioDAO (para la validación de unicidad y persistencia).
 */
public class Main {

    private static UsuarioDAO usuarioDAO = new UsuarioDAOjdbc();
    private static Scanner scanner = new Scanner(System.in);

    public static void registrarUsuario() {

        // 1. SOLICITAR DATOS
        Usuario nuevoUsuario = new Usuario();
        solicitarDatosUsuario(nuevoUsuario);
        try {
            // 2. VALIDACIÓN DE UNICIDAD DE DNI (Lógica de Persistencia en DAO)
            GestionUsuario.validacionDatos(nuevoUsuario);

            // 3. MOSTRAR Y CONFIRMAR al usuario
            mostrarDatosIngresados(nuevoUsuario);
            System.out.println("\n¿Son estos datos correctos? (S/N): ");
            String confirmacion = scanner.nextLine();

            if ("S".equals(confirmacion)) {
                // 4. GUARDAR EN LA BASE DE DATOS
                usuarioDAO.registrar(nuevoUsuario);
                System.out.println("\n¡REGISTRO EXITOSO! Los datos se han guardado correctamente.");
            } else {
                System.out.println("\nRegistro cancelado por el usuario. No se guardó en la Base de Datos.");
            }

        } catch (SQLException e) {
            System.err.println("\n[ERROR DE BD] Falló la operación de la base de datos: " + e.getMessage());
        }
    }

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

    private static void solicitarDatosUsuario(Usuario nuevoUsuario) {
        System.out.println("Ingrese DNI:");
        nuevoUsuario.setDNI(scanner.nextInt());
        scanner.nextLine(); // Consumir el salto de línea pendiente
        System.out.println("Ingrese Nombre:");
        nuevoUsuario.setNombre(scanner.nextLine());
        System.out.println("Ingrese Apellido:");
        nuevoUsuario.setApellido(scanner.nextLine());
        System.out.println("Ingrese username:");
        nuevoUsuario.setUsername(scanner.nextLine());
        System.out.println("Ingrese Correo:");
        nuevoUsuario.setCorreo(scanner.nextLine());
        System.out.println("Ingrese Contrasenia:");
        nuevoUsuario.setContrasenia(scanner.nextLine());
        System.out.println("Ingrese Dia de Nacimiento:");
        Fecha f = new Fecha();
        f.setDia(scanner.nextInt());
        System.out.println("Ingrese Mes de Nacimiento:");
        f.setMes(scanner.nextInt());
        System.out.println("Ingrese Año de Nacimiento:");
        f.setAnio(scanner.nextInt());
        nuevoUsuario.setFechaNacimiento(f);

    }

    public static void main(String[] args) {
        try {
            // Inicializar la conexión (opcional, pero buena práctica)
            conexion.conectar();
            registrarUsuario();
        } catch (SQLException e) {
            System.out.println("Error al iniciar la aplicación: La conexión a la BD falló.");
        }

        finally {
            conexion.desconectar(); // Cierra la conexión al finalizar
            scanner.close();
        }
    }
}
