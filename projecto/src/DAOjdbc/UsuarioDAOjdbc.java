package DAOjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.UsuarioDAO;
import DB.BaseDeDatos;
import model.Usuario;

public class UsuarioDAOjdbc implements UsuarioDAO {

    // Constante para el SQL con JOIN, evita repetir codigo
    private static final String SELECT_USUARIO_CON_DATOS = 
        "SELECT U.ID, U.NOMBRE_USUARIO, U.EMAIL, U.CONTRASENIA, " +
        "DP.ID AS DP_ID, DP.NOMBRES, DP.APELLIDO, DP.DNI " +
        "FROM USUARIO U " +
        "JOIN DATOS_PERSONALES DP ON U.ID_DATOS_PERSONALES = DP.ID "; // [cite: 85-86]

    @Override
    public void registrar(Usuario usuario) {
        // [cite: 79-87]
        String sql = "INSERT INTO USUARIO (NOMBRE_USUARIO, EMAIL, CONTRASENIA, ID_DATOS_PERSONALES) VALUES (?, ?, ?, ?)";
        try (Connection conn = BaseDeDatos.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, usuario.getUsername());
            pstmt.setString(2, usuario.getCorreo());
            pstmt.setString(3, usuario.getContrasenia());
            pstmt.setInt(4, usuario.getID_DATOS_PERSONALES());
            
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
        }
    }

    @Override
    public Usuario buscarPorId(int id) {
        String sql = SELECT_USUARIO_CON_DATOS + "WHERE U.ID = ?";
        try (Connection conn = BaseDeDatos.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToUsuario(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar usuario: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Usuario> listarTodos() {
        List<Usuario> lista = new ArrayList<>();
        String sql = SELECT_USUARIO_CON_DATOS; // 

        try (Connection conn = BaseDeDatos.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                lista.add(mapResultSetToUsuario(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar usuarios: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void actualizar(Usuario usuario) {
        String sql = "UPDATE USUARIO SET NOMBRE_USUARIO = ?, EMAIL = ?, CONTRASENIA = ?, ID_DATOS_PERSONALES = ? WHERE ID = ?";
        try (Connection conn = BaseDeDatos.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
           pstmt.setString(1, usuario.getUsername());
            pstmt.setString(2, usuario.getCorreo());
            pstmt.setString(3, usuario.getContrasenia());
            pstmt.setInt(4, usuario.getID_DATOS_PERSONALES());
            pstmt.setInt(5, usuario.getID_USUARIO()); // ID para el WHERE
            
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error al actualizar usuario: " + e.getMessage());
        }
    }

    @Override
    public Usuario validar(String nombreUsuario, String contrasenia) {
        // 
        String sql = SELECT_USUARIO_CON_DATOS + "WHERE U.NOMBRE_USUARIO = ? AND U.CONTRASENIA = ?";
        try (Connection conn = BaseDeDatos.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, nombreUsuario);
            pstmt.setString(2, contrasenia);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToUsuario(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al validar usuario: " + e.getMessage());
        }
        return null; // Credenciales incorrectas
    }

    /**
     * Helper para convertir un ResultSet (con JOIN) en un objeto Usuario.
     */
    private Usuario mapResultSetToUsuario(ResultSet rs) throws SQLException {
        
        Usuario usuario = new Usuario();
        usuario.setID_USUARIO(rs.getInt("ID"));
        usuario.setUsername(rs.getString("NOMBRE_USUARIO"));
        usuario.setCorreo(rs.getString("EMAIL"));
        usuario.setContrasenia(rs.getString("CONTRASENIA"));
        usuario.setID_USUARIO(rs.getInt("DP_ID"));
        usuario.setNombre(rs.getString("NOMBRES"));
        usuario.setApellido(rs.getString("APELLIDO"));
        usuario.setDNI(rs.getInt("DNI"));
        return usuario;
    }

    @Override
    public boolean existeDNI(int dni) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

