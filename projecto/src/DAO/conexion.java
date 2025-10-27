package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
            
    private static final String DB_URL = "jdbc:sqlite:plataforma_streaming.db";

    public static Connection conectar() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
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
