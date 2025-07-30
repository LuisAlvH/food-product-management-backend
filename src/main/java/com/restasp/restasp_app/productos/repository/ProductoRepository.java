package com.restasp.restasp_app.productos.repository;
import java.util.List;
import java.util.Optional;
import com.restasp.restasp_app.productos.model.Producto;


public interface ProductoRepository {

    void add(Producto producto);
    Optional<Producto> getId(int id);
    List<Producto> getAll();
    void delete(int id) ;
    Producto update( Producto producto);

}
