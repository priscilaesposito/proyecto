// Archivo: src/daojdbc/DatosPersonalesDAOJdbc.java
package DAOjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.DatosPersonalesDAO;
import DB.BaseDeDatos;
import model.Usuario;

public class DatosPersonalesDAOjdbc implements DatosPersonalesDAO {

    @Override
    public void registrar(Usuario datos) {
        // [cite: 63-68]
        String sql = "INSERT INTO DATOS_PERSONALES (NOMBRES, APELLIDO, DNI) VALUES (?, ?, ?)";
        try (Connection conn = BaseDeDatos.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, datos.getNombre());
            pstmt.setString(2, datos.getApellido());
            pstmt.setInt(3, datos.getDni());
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error al registrar datos personales: " + e.getMessage());
        }
    }

    @Override
    public DatosPersonales buscarPorId(int id) {
        String sql = "SELECT * FROM DATOS_PERSONALES WHERE ID = ?";
        try (Connection conn = BaseDeDatos.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToDatosPersonales(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar datos personales: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<DatosPersonales> listarTodos() {
        List<DatosPersonales> lista = new ArrayList<>();
        String sql = "SELECT * FROM DATOS_PERSONALES";
        try (Connection conn = BaseDeDatos.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                lista.add(mapResultSetToDatosPersonales(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar datos personales: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void actualizar(Usuario datos) {
        String sql = "UPDATE DATOS_PERSONALES SET NOMBRES = ?, APELLIDO = ?, DNI = ? WHERE ID = ?";
        try (Connection conn = BaseDeDatos.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, datos.getNombres());
            pstmt.setString(2, datos.getApellido());
            pstmt.setInt(3, datos.getDni());
            pstmt.setInt(4, datos.getId()); // ID para el WHERE
            
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error al actualizar datos personales: " + e.getMessage());
        }
    }

    @Override
    public Usuario buscarPorDni(int dni) {
        String sql = "SELECT * FROM DATOS_PERSONALES WHERE DNI = ?";
        try (Connection conn = BaseDeDatos.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, dni);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToDatosPersonales(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar por DNI: " + e.getMessage());
        }
        return null;
    }

    /**
     * Helper para convertir un ResultSet en un objeto DatosPersonales.
     */
    private Usuario mapResultSetToDatosPersonales(ResultSet rs) throws SQLException {
        Usuario datos = new Usuario();
        datos.setId(rs.getInt("ID"));
        datos.setNombres(rs.getString("NOMBRES"));
        datos.setApellido(rs.getString("APELLIDO"));
        datos.setDni(rs.getInt("DNI"));
        return datos;
    }

    

}