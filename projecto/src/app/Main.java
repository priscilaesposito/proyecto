package app;

import java.util.Scanner;
import java.sql.SQLException;

import DAO.conexion;


import DB.BaseDeDatos;

public class Main {

    private static void menu(){
        System.out.println("Seleccione una opción:");
            System.out.println("1. Registrar Datos Personales");
            System.out.println("2. Registrar Usuario");
            System.out.println("3. Registrar Película");
            System.out.println("4. Listar Usuarios");
            System.out.println("5. Listar Películas");
            System.out.println("6. Registrar Reseña");
            System.out.println("7. Aprobar Reseña");
            System.out.println("8. Salir");

    }
     private static void opciones(int op) throws Exception {
        switch (op) {
            case 1:
                logica.registrarDatosPersonales();
                break;
            case 2:
                logica.registrarUsuario();
                break;
            case 3:
                logica.registrarPelicula();
                break;
            case 4:
                logica.listarUsuarios();
                break;
            case 5:
                logica.listarPeliculas();
                break;
            case 6:
                logica.registrarResenia();
                break;
            case 7:
                logica. aprobarResenia();
                break;
            case 8:
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        try {
            
            conexion.conectar(); //CONECTAR
            BaseDeDatos.inicializarBaseDeDatos();

            //menu para a elegir modulo
            menu();
            int op = scanner.nextInt();
            scanner.nextLine(); //SALTO DE LINEA
            while (op!=8){
            opciones(op);
            menu();
            op = scanner.nextInt();
            scanner.nextLine(); //SALTO DE LINEA

        }

        } 
        catch (SQLException e) {
            System.out.println("Error al iniciar la aplicación: La conexión a la BD falló.");
        } 

        finally {
            conexion.desconectar(); // Cierra la conexion
            scanner.close();
        }
    }


}
