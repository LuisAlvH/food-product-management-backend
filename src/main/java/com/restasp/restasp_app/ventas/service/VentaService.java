package com.restasp.restasp_app.ventas.service;
import java.util.List;
import java.util.Optional;
import com.restasp.restasp_app.ventas.model.Venta;

public interface VentaService {
        List<Venta> listarVentas();
        Optional<Venta> obtenerVentaPorId(int id); 
        void addVenta(Venta venta);   
}
