package com.restasp.restasp_app.usuarios.service;
import java.util.List;
import java.util.Optional;
import com.restasp.restasp_app.usuarios.model.Usuario;

public interface UsuarioService {
        List<Usuario> listarUsuarios();
        Optional<Usuario> obtenerUsuarioPorId(int id); 
        void addUsuario(Usuario usuario);   
        void eliminarUsuario(int id);
        Usuario actualizarUsuario(int id,Usuario usuario);
}
