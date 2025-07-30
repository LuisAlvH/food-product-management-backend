package com.restasp.restasp_app.productos.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restasp.restasp_app.productos.model.Producto;
import com.restasp.restasp_app.productos.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    @Autowired
    public ProductoServiceImpl(ProductoRepository productoRepository) {
            this.productoRepository = productoRepository;
        
    }
    @Override
    public List<Producto> listarProductos() {
         return productoRepository.getAll();
        
    }
    @Override
    public Optional<Producto> obtenerProductoPorId(int id) {
        return productoRepository.getId(id);
    }
    @Override
    public void addProducto(Producto producto) {
        productoRepository.add(producto);
    }
    @Override
    public void eliminarProducto(int id) {
       productoRepository.delete(id);
    }

    @Override
    public Producto actualizarProducto(int id, Producto producto) {
        producto.setId_producto(id);
        Producto productoActualizado=productoRepository.update(producto);
        if (productoActualizado == null)throw new RuntimeException("No se encontró el producto para actualizar con id " + id);  
            return productoActualizado;
       
    }

}

