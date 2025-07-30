package com.restasp.restasp_app.productos.service;
import java.util.List;
import java.util.Optional;
import com.restasp.restasp_app.productos.model.Producto;


public interface ProductoService {
        List<Producto> listarProductos();
        Optional<Producto> obtenerProductoPorId(int id); 
        void addProducto(Producto producto);   
        void eliminarProducto(int id);
        Producto actualizarProducto(int id,Producto producto);
}
