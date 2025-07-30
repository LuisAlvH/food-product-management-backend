package com.restasp.restasp_app.usuarios.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restasp.restasp_app.usuarios.model.Usuario;
import com.restasp.restasp_app.usuarios.repository.UsuarioRepository;


@Service
public class UsuarioServiceImpl  implements UsuarioService {

private final UsuarioRepository usuarioRepository;


@Autowired
public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
     
}



@Override
public void addUsuario(Usuario usuario) {

        usuarioRepository.add(usuario);

}

@Override
public List<Usuario> listarUsuarios() {
        return usuarioRepository.getAll();
        
}


@Override
public Optional<Usuario> obtenerUsuarioPorId(int id) {
        return usuarioRepository.getId(id);
}



@Override
public void eliminarUsuario(int id) {
        usuarioRepository.delete(id);
}



@Override
public Usuario actualizarUsuario(int id, Usuario usuario) {
        usuario.setId_usuario(id);
    Usuario usuarioActualizado = usuarioRepository.update(usuario);
     if (usuarioActualizado == null)throw new RuntimeException("No se encontró el usuario para actualizar con id " + id);      
        return usuarioActualizado;
}




}
