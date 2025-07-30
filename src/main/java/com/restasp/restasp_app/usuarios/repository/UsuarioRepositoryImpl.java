package com.restasp.restasp_app.usuarios.repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.restasp.restasp_app.BdRestaSp.SqliteSingleton;
import com.restasp.restasp_app.usuarios.model.Usuario;

@Repository
public class UsuarioRepositoryImpl  implements UsuarioRepository{

    private Connection conn;

    public  UsuarioRepositoryImpl (){
        conn=SqliteSingleton.getInstance().getConnection();

    }
    @Override
    public void add(Usuario usuario){
        final String passwordDefec="usuario123";
        String sql = "INSERT INTO usuarios(nombre,rol,username,password) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getRol());
            pstmt.setString(3, usuario.getUser());
            pstmt.setString(4, passwordDefec);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar usuario: " + e.getMessage());
        }

    }
    @Override
    public Optional<Usuario> getId(int id) {
        String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("nombre"),
                    rs.getString("rol"),
                    rs.getString("username"),
                    rs.getString("fecha_creacion")
                );
                return Optional.of(usuario); 
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener usuario: " + e.getMessage());
        }

        return Optional.empty();  
    }

    @Override
    public List<Usuario> getAll(){

         List<Usuario> usuarios = new ArrayList<>();
         
        String sql = "SELECT * FROM usuarios";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                usuarios.add(new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("nombre"),
                    rs.getString("rol"),
                    rs.getString("username"),
                    rs.getString("fecha_creacion")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Error al listar usuarios: " + e.getMessage());
        }

        return usuarios;

    }
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
        }
    }
    @Override
    public Usuario update(Usuario usuario) {
        String sqlUsuario = "UPDATE usuarios SET nombre = ?, username = ? WHERE id_usuario= ? RETURNING id_usuario, nombre, username ,rol ,fecha_creacion";

           try (PreparedStatement pstmt = conn.prepareStatement(sqlUsuario)) {
                pstmt.setString(1, usuario.getNombre());  
                pstmt.setString(2, usuario.getUser());
                pstmt.setInt(3, usuario.getId_usuario());
                
        
                try(ResultSet rs =pstmt.executeQuery()){

                    if (rs.next()) {
                
                    return new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("rol"),
                        rs.getString("username"),
                        rs.getString("fecha_creacion")
                    );
                    } 
                }


        
        } catch (SQLException e) {
            System.err.println("Error al actualizar el usuario: " + e.getMessage());
        }
       return null;

        
    }


}
