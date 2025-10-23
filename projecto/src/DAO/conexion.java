package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
            
    // Nombre del archivo de la base de datos
    private static final String DB_URL = "jdbc:sqlite:plataforma_streaming.db";

    /**
     * Establece una conexión con la base de datos SQLite.
     * Si el archivo de la base de datos no existe, SQLite lo creará.
     * @return un objeto Connection
     * @throws SQLException si hay un error al conectar con la base de datos
     */
    public static Connection conectar() throws SQLException {
        Connection connection = null;
        try {
            // Cargar el driver JDBC de SQLite
            Class.forName("org.sqlite.JDBC");
            // Crear la conexión
            connection = DriverManager.getConnection(DB_URL);
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Driver JDBC de SQLite no encontrado: " + e.getMessage());
            throw new SQLException("Driver JDBC no encontrado", e);
        }
        return connection;
    }

    public static void desconectar() {
        try {
            Connection connection = conectar();
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al desconectar de la base de datos SQLite: " + e.getMessage());
        }
    }

}
