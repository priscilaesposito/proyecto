package app;

import model.Usuario;

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
import model.Administrador;

import java.sql.SQLException;

public class Main {

    private static UsuarioDAO usuarioDAO = new UsuarioDAOjdbc();
    private static DatosPersonalesDAO datosPersonalesDAO = new DatosPersonalesDAOJdbc();
    private static Scanner scanner = new Scanner(System.in);
    private static GestionUsuario gestionUsuario = new GestionUsuario();
    private static TL2 TL2 = new TL2();
    private static Administrador Administrador = new Administrador();

    private static void registrarDatosPersonales() throws Exception {
        
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

    private static void registrarUsuario() throws Exception {
        
    
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

    private static void registrarPelicula() throws Exception {
        
    
        model.Pelicula p = new model.Pelicula();
       
        try {
            // 1. SOLICITAR DATOS DE LA PELÍCULA
           solicitarDatosPelicula(p);
            Administrador.validarRegistroPelicula(p);

            // 2. MOSTRAR Y CONFIRMAR al usuario
            mostrarDatosPelicula(p);
            System.out.println("\n¿Son estos datos correctos? (S/N): ");
            String confirmacion = scanner.nextLine();

            if ("S".equals(confirmacion)) {
                // 3. GUARDAR EN LA BASE DE DATOS
                Administrador.almacenarPelicula(p);
                System.out.println("\n¡REGISTRO EXITOSO! Los datos se han guardado correctamente.");
            } else {
                System.out.println("\nRegistro cancelado por el usuario. No se guardó en la Base de Datos.");
            }

        } 
        catch (SQLException e) {
            System.err.println("\n[ERROR DE BD] Falló la operación de la base de datos: " + e.getMessage());
        }
    }

    private static void listarUsuarios() throws Exception {
        try {
            System.out.println("Seleccione el criterio de ordenamiento:");
            System.out.println("NOMBRE O EMAIL");
            String criterio = scanner.nextLine();
            List<Usuario> usuarios = TL2.listarUsuariosOrdenados(criterio);
            System.out.println("\n--- LISTA DE USUARIOS REGISTRADOS ---");
            for (Usuario u : usuarios) {
                System.out.println("ID: " + u.getID_USUARIO() + ", Username: " + u.getUsername() + ", Correo: " + u.getCorreo() +
                        ", Nombre: " + u.getNombre() + " " + u.getApellido() + ", DNI: " + u.getDNI());
            }
        } 
        catch (SQLException e) {
            System.err.println("\n[ERROR DE BD] Falló la operación de la base de datos: " + e.getMessage());
        }
    }

    private static void listarPeliculas() throws Exception {
        try {
            System.out.println("Seleccione el criterio de ordenamiento:");
            System.out.println("TITULO, DIRECTOR O GENERO");
            String criterio = scanner.nextLine();
            List<model.Pelicula> peliculas = TL2.listarPeliculasOrdenadas(criterio);
            System.out.println("\n--- LISTA DE PELÍCULAS REGISTRADAS ---");
            for (model.Pelicula p : peliculas) {
                System.out.println("ID: " + p.getID() + ", Título: " + p.getMetadatos().getTitulo() + ", Director: " + p.getMetadatos().getDirector() +
                        ", Género(s): " + String.join(", ", p.getGeneros()) + ", Duración: " + p.getVideo().getDuracion() + " minutos");
            }
        } 
        catch (SQLException e) {
            System.err.println("\n[ERROR DE BD] Falló la operación de la base de datos: " + e.getMessage());
        }
    }

    private static void mostrarDatosPelicula(model.Pelicula p) {
        System.out.println("\n--- DATOS INGRESADOS DE LA PELÍCULA ---");
        System.out.println("Género(s): " + String.join(", ", p.getGeneros()));
        System.out.println("Título: " + p.getMetadatos().getTitulo());
        System.out.println("Resumen: " + p.getMetadatos().getSipnosis());
        System.out.println("Director: " + p.getMetadatos().getDirector());
        System.out.println("Duración (minutos): " + p.getVideo().getDuracion());
    }   
    private static void solicitarDatosPelicula(model.Pelicula p){
        System.out.println("Ingrese Género(s) (separados por comas si son varios):");
        String generosInput = scanner.nextLine();
        String[] generosArray = generosInput.split(",");
        for (String genero : generosArray) {
            p.anadirGeneros(genero.trim());
        }
        System.out.println("Ingrese Título:");
        p.getMetadatos().setTitulo(scanner.nextLine());
        System.out.println("Ingrese Resumen:");
        p.getMetadatos().setSipnosis(scanner.nextLine());
        System.out.println("Ingrese Director:");
        p.getMetadatos().setDirector(scanner.nextLine());
        System.out.println("Ingrese Duración (en minutos):");
        p.getVideo().setDuracion(scanner.nextDouble());
        scanner.nextLine(); // Consumir el salto de línea pendiente
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


    public static void main(String[] args) throws Exception {
        try {
            // Inicializar la conexión (opcional, pero buena práctica)
            conexion.conectar(); 
            BaseDeDatos.inicializarBaseDeDatos();
            //menu para a elegir modulo
            System.out.println("Seleccione una opción:");
            System.out.println("1. Registrar Datos Personales");
            System.out.println("2. Registrar Usuario");
            int op = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea pendiente

            switch (op) {
                case 1:
                    registrarDatosPersonales();
                    break;
                case 2:
                    registrarUsuario();
                    break;
                case 3:
                    registrarPelicula();
                    break;
                case 4:
                    listarUsuarios();
                    break;
                case 5:
                    listarPeliculas();
                     break;
                case 6:
                    //registrarResenia();
                     break;
                case 7:
                    //aprobarResenia();
                     break;
                default:
                    System.out.println("Opción no válida. Saliendo del programa.");
                    break;
            }

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
