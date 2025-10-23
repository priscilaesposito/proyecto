// Archivo: src/db/BaseDeDatos.java
package DB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import DAO.conexion;

public class BaseDeDatos {

    /**
     * Método público para obtener conexión a la base de datos.
     * Utiliza la clase conexion para establecer la conexión.
     * @return Connection objeto de conexión a la base de datos
     */
    public static Connection conectar() {
        return conexion.conectar();
    }

    /**
     * Crea las tablas en la base de datos si no existen.
     * Este método debe llamarse una vez al inicio de la aplicación.
     */
    public static void inicializarBaseDeDatos() {
        // [cite: 60-61]
        try (Connection connection = conectar();
             Statement stmt = connection.createStatement()) {

            // SQL para crear DATOS_PERSONALES [cite: 63-69]
            String sqlDatosPersonales = "CREATE TABLE IF NOT EXISTS DATOS_PERSONALES (" +
                    "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                    "NOMBRES TEXT (100) NOT NULL, " +
                    "APELLIDO TEXT (100) NOT NULL, " +
                    "DNI INTEGER NOT NULL" +
                    ");";
            stmt.executeUpdate(sqlDatosPersonales);

            // SQL para crear PELICULA [cite: 70-78]
            String sqlPelicula = "CREATE TABLE IF NOT EXISTS PELICULA (" +
                    "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                    "GENERO TEXT (1) NOT NULL, " +
                    "TITULO TEXT (100) NOT NULL, " +
                    "RESUMEN TEXT (500), " +
                    "DIRECTOR TEXT (100) NOT NULL, " +
                    "DURACION REAL NOT NULL" +
                    ");";
            stmt.executeUpdate(sqlPelicula);

            // SQL para crear USUARIO [cite: 79-88]
            String sqlUsuario = "CREATE TABLE IF NOT EXISTS USUARIO (" +
                    "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                    "NOMBRE_USUARIO TEXT NOT NULL, " +
                    "EMAIL TEXT NOT NULL, " +
                    "CONTRASENIA TEXT NOT NULL, " +
                    "ID_DATOS_PERSONALES INTEGER NOT NULL, " +
                    "CONSTRAINT USUARIO_DATOS_PERSONALES_FK FOREIGN KEY (ID_DATOS_PERSONALES) " + // Corregido de (ID) a (ID_DATOS_PERSONALES)
                    "REFERENCES DATOS_PERSONALES (ID)" +
                    ");";
            stmt.executeUpdate(sqlUsuario);

            // SQL para crear RESENIA [cite: 92]
            String sqlResenia = "CREATE TABLE IF NOT EXISTS RESENIA (" +
                    "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                    "CALIFICACION INTEGER NOT NULL, " +
                    "COMENTARIO TEXT(500), " +
                    "APROBADO INTEGER DEFAULT (1) NOT NULL, " +
                    "FECHA_HORA DATETIME NOT NULL, " +
                    "ID_USUARIO INTEGER NOT NULL, " +
                    "ID_PELICULA INTEGER NOT NULL, " +
                    "CONSTRAINT RESENIA_USUARIO_FK FOREIGN KEY (ID_USUARIO) " +
                    "REFERENCES USUARIO(ID), " +
                    "CONSTRAINT RESENIA_PELICULA_FK FOREIGN KEY (ID_PELICULA) " +
                    "REFERENCES PELICULA (ID)" +
                    ");";
            stmt.executeUpdate(sqlResenia);
            
            // System.out.println("Base de datos inicializada y tablas creadas (si no existían).");

        } catch (SQLException e) {
            System.out.println("Error al crear las tablas: " + e.getMessage());
        }
    }
}
