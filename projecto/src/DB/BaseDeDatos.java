package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDeDatos {

    private String url = "jdbc:sqlite:plataforma_streaming.db";

    /**
     * Este método establece la conexión y crea las tablas.
     */
    public void inicializarBaseDeDatos() {
        
        // 'try-with-resources' se asegura de que la conexión se cierre sola
        try (Connection connection = DriverManager.getConnection(url)) {
            
            if (connection != null) {
                System.out.println("¡Conexión a SQLite establecida!");
                
                // Una vez conectados, llamamos al método que crea las tablas
                this.creacionDeTablasEnBD(connection); 
            }

        } catch (SQLException e) {
            System.err.println("Error al conectar o crear tablas: " + e.getMessage());
        }
    }
    
    /**
     * Este metodo se encarga de la creación de las tablas donde se
     * almacenara la
     * informacion de los objetos. Una vez establecida una conexion, deberia
     * ser lo proximo a ser ejecutado.
     * @param connection objeto conexion a la base de datos SQLite
     * @throws SQLException
     */
    private void creacionDeTablasEnBD(Connection connection) throws SQLException { // [cite: 60]
        Statement stmt = connection.createStatement(); // [cite: 61, 62]
        
        // --- Tabla DATOS_PERSONALES ---
        String sql = " CREATE TABLE IF NOT EXISTS DATOS_PERSONALES (" + // [cite: 63]
                     "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " + // [cite: 65]
                     "NOMBRES TEXT (100) NOT NULL, " + // [cite: 66]
                     "APELLIDO TEXT (100) NOT NULL, " + // [cite: 67]
                     "DNI INTEGER NOT NULL" + // [cite: 68]
                     ");";
        stmt.executeUpdate(sql); // [cite: 69]

        // --- Tabla PELICULA ---
        sql = " CREATE TABLE IF NOT EXISTS PELICULA (" + // [cite: 70]
              "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " + // [cite: 71]
              "GENERO TEXT (1) NOT NULL, " + // [cite: 72]
              "TITULO TEXT (100) NOT NULL, " + // [cite: 73]
              "RESUMEN TEXT (500)," + // [cite: 74]
              "DIRECTOR TEXT (100) NOT NULL, " + // [cite: 75]
              "DURACION REAL NOT NULL" + // [cite: 76]
              ");";
        stmt.executeUpdate(sql); // [cite: 78]

        // --- Tabla USUARIO ---
        sql = " CREATE TABLE IF NOT EXISTS USUARIO (" + // [cite: 79]
              "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " + // [cite: 80]
              "NOMBRE_USUARIO TEXT NOT NULL, " + // [cite: 81]
              "EMAIL TEXT NOT NULL, " + // [cite: 82]
              "CONTRASENIA TEXT NOT NULL, ID_DATOS_PERSONALES INTEGER NOT NULL, " + // [cite: 83, 84]
              "CONSTRAINT USUARIO_DATOS_PERSONALES_FK FOREIGN KEY (ID) " + // [cite: 85]
              "REFERENCES DATOS_PERSONALES (ID)" + // [cite: 86]
              ");";
        stmt.executeUpdate(sql); // [cite: 88]

        // --- Tabla RESENIA ---
        sql = " CREATE TABLE IF NOT EXISTS RESENIA (" +
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
        stmt.executeUpdate(sql); // [cite: 92]
        
        stmt.close(); // [cite: 92]
        
        System.out.println("¡Tablas creadas con éxito!");
    }
}
