
package com.restasp.restasp_app.ventas.repository;
import java.util.List;
import java.util.Optional;

import com.restasp.restasp_app.ventas.model.Venta;



public interface VentaRepository {
     void add(Venta venta); 
     Optional<Venta> getId(int id);
     List<Venta> getAll();
}
