package app;

import model.Usuario;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import DAO.DatosPersonalesDAO;
import DAO.UsuarioDAO;
import DAO.conexion;
import DAOjdbc.DatosPersonalesDAOJdbc;
import model.GestionUsuario;
import model.TL2;
import DAOjdbc.UsuarioDAOjdbc;
import DB.BaseDeDatos;

import java.sql.SQLException;
import java.util.Arrays;

public class Main {

    private static UsuarioDAO usuarioDAO = new UsuarioDAOjdbc();
    private static DatosPersonalesDAO datosPersonalesDAO = new DatosPersonalesDAOJdbc();
    private static Scanner scanner = new Scanner(System.in);
    private static GestionUsuario gestionUsuario = new GestionUsuario();
    private static TL2 TL2 = new TL2();

    public static void registrarDatosPersonales() throws Exception {
        
        // 1. SOLICITAR DATOS 
        Usuario DatosPersonales = new Usuario();
        solicitarDatosPersonales(DatosPersonales);
        try {
            
            // 2. VALIDACIÓN DE UNICIDAD DE DNI (Lógica de Persistencia en DAO)
            gestionUsuario.validacionDatosPersonales(DatosPersonales);

            // 3. MOSTRAR Y CONFIRMAR al usuario
            mostrarDatosIngresados(DatosPersonales);
            System.out.println("\n¿Son estos datos correctos? (S/N): ");
            String confirmacion = scanner.nextLine();

            if ("S".equals(confirmacion)) {
                // 4. GUARDAR EN LA BASE DE DATOS
                gestionUsuario.registrarDatosPersonales(DatosPersonales);
                System.out.println("\n¡REGISTRO EXITOSO! Los datos se han guardado correctamente.");
            } else {
                System.out.println("\nRegistro cancelado por el usuario. No se guardó en la Base de Datos.");
            }

        } 
        catch (SQLException e) {
            System.err.println("\n[ERROR DE BD] Falló la operación de la base de datos: " + e.getMessage());
        }
    }

    public static void registrarUsuario() throws Exception {
        
    
        Usuario u = new Usuario();
       
        try {
            // 1. Listado y eleccion de datos personales existentes
            List<Usuario> DP= TL2.getListaPersonas();

            for (Usuario d : DP) {
                System.out.println("ID: " + d.getID_DATOS_PERSONALES() + " - Nombre: " + d.getNombre() + " " + d.getApellido() + " - DNI: " + d.getDNI());
            }
           
            // 2. SELECCIONAR DATOS PERSONALES EXISTENTES
            System.out.println("\nSeleccione el ID de los datos personales que desea asociar al usuario:");
            int idSeleccionado = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea pendiente
            
            //3. SOLICITAR DATOS DEL USUARIO
             solicitarDatosUsuario(u);
             gestionUsuario.ValidacionUsuario(u);
             u.setID_DATOS_PERSONALES(idSeleccionado);

            // 3. MOSTRAR Y CONFIRMAR al usuario
            mostrarUsuarioIngresados(u);
            System.out.println("\n¿Son estos datos correctos? (S/N): ");
            String confirmacion = scanner.nextLine();

            if ("S".equals(confirmacion)) {
                // 4. GUARDAR EN LA BASE DE DATOS
                gestionUsuario.registrarUsuario(u);
                System.out.println("\n¡REGISTRO EXITOSO! Los datos se han guardado correctamente.");
            } else {
                System.out.println("\nRegistro cancelado por el usuario. No se guardó en la Base de Datos.");
            }

        } 
        catch (SQLException e) {
            System.err.println("\n[ERROR DE BD] Falló la operación de la base de datos: " + e.getMessage());
        }
    }
    

    private static void mostrarDatosIngresados(Usuario u) {
        System.out.println("\n--- DATOS INGRESADOS ---");
        System.out.println("DNI: " + u.getDNI());
        System.out.println("Nombre: " + u.getNombre());
        System.out.println("Apellido: " + u.getApellido());
    }
    private static void mostrarUsuarioIngresados(Usuario u) {
        System.out.println("\n--- DATOS DE USUARIO ---");
        System.out.println("Username: " + u.getUsername());
        System.out.println("Correo: " + u.getCorreo());
        System.out.println("Contraseña: " + u.getContrasenia());
    }

    private static void solicitarDatosPersonales(Usuario nuevoUsuario){
        System.out.println("Ingrese DNI:");
        nuevoUsuario.setDNI(scanner.nextInt());
        scanner.nextLine(); // Consumir el salto de línea pendiente
        System.out.println("Ingrese Nombre:");
        nuevoUsuario.setNombre(scanner.nextLine());
        System.out.println("Ingrese Apellido:");
        nuevoUsuario.setApellido(scanner.nextLine());

    }

    private static void solicitarDatosUsuario(Usuario nuevoUsuario){
         System.out.println("Ingrese username:");
        nuevoUsuario.setUsername(scanner.nextLine());
        System.out.println("Ingrese Correo:");
        nuevoUsuario.setCorreo(scanner.nextLine());
        System.out.println("Ingrese Contrasenia:");
        nuevoUsuario.setContrasenia(scanner.nextLine());
    }

    private static void Valido(String linea) {
    	LinkedList<Character> caracteresInvalidos = new LinkedList<>(Arrays.asList('0','1','2','3','4','5','6','7','8','9','!','"','#','$','%','&','/','(',')','=','?','¡','¿','+','-','*','{','}','[',']','^','`','´','¨',';',':','.',','));
    	for (char c : linea.toCharArray()) {
    		if (caracteresInvalidos.contains(c)) {
    			System.out.println("El nombre no debe contener números ni caracteres especiales. Ingrese nuevamente:");
    			String nuevaLinea = scanner.nextLine();
    			Valido(nuevaLinea);
    			return;
    		}
    	}
    	return;
    }

    public static void main(String[] args) throws Exception {
        try {
            // Inicializar la conexión (opcional, pero buena práctica)
            conexion.conectar(); 
            BaseDeDatos.inicializarBaseDeDatos();
            //menu para a elegir modulo
            
            registrarDatosPersonales();
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
