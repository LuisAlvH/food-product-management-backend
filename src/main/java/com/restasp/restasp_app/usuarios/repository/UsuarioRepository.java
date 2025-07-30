package com.restasp.restasp_app.usuarios.repository;
import java.util.List;
import java.util.Optional;
import com.restasp.restasp_app.usuarios.model.Usuario;

public interface UsuarioRepository {

     void add(Usuario usuario); 
     Optional<Usuario> getId(int id);
     List<Usuario> getAll();
     void delete(int id) ;
     Usuario update( Usuario usuario);

}
